package com.tweener.charts

import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tweener.common._internal.kotlinextensions.degrees
import com.tweener.common._internal.kotlinextensions.radians
import com.tweener.common._internal.utils.Degrees
import com.tweener.czan.theme.Size
import kotlin.math.atan2
import kotlin.math.min

/**
 * @author Vivien Mahe
 * @since 05/03/2024
 */

data class Segment(
    val id: String,
    val angle: Degrees,
    @FloatRange(from = 0.0, to = 1.0) val progress: Float = 1f,
    val baseColor: Color,
    val backgroundColor: Color = baseColor.copy(alpha = 0.1f),
    val enabled: Boolean = true,
)

const val NO_SELECTED_SEGMENT = -1

/**
 * Draws a donut chart with the provided list of [Segment]s, with the first segment starting from the given [startAngleFromOrigin] in degrees.
 *
 * @param startAngleFromOrigin The angle between the right part of the horizontal axis of the circle (0 or 2*PI) and the starting point of the first segment.
 * @param sizes [DonutChartSizes] that will be used to resolve the sizes for this chart. See [DonutChartDefault.chartSizes].
 * @param animated Whether or not the segments should be animated on composition.
 * @param animationDurationMillis Duration of the animation for the segments.
 */
@Composable
fun DonutChart(
    segments: List<Segment>,
    modifier: Modifier = Modifier,
    startAngleFromOrigin: Degrees = 270f.degrees,
    sizes: DonutChartSizes = DonutChartDefault.chartSizes(),
    animated: Boolean = true,
    animationDurationMillis: Int = 300,
    onSegmentClicked: ((Segment) -> Unit)? = null,
) {
    val density = LocalDensity.current
    val initialSegmentDrawingAnimRatio = if (LocalInspectionMode.current) 1f else 0f // Don't animate when in Preview mode

    val segmentsStartAngles = remember { mutableStateListOf<Degrees>() } // List of all segments starting angles
    val segmentsEndAngles = remember { mutableStateListOf<Degrees>() } // List of all segments ending angles
    val segmentDrawingAnimRatio = remember { Animatable(initialValue = initialSegmentDrawingAnimRatio) } // Ratio of a segment to be drawn when animating segments
    var clickedSegmentIndex by remember { mutableIntStateOf(NO_SELECTED_SEGMENT) } // Clicked segment in chart

    LaunchedEffect(segments) {
        // Compute each segment's starting and ending angles on the circle
        segmentsStartAngles.clear()
        segmentsEndAngles.clear()

        var currentAngle = sizes.angleBetweenSegments() / 2
        segments.forEach { segment ->
            segmentsStartAngles.add(currentAngle)
            currentAngle += segment.angle // Move clockwise
            segmentsEndAngles.add(currentAngle - sizes.angleBetweenSegments())
        }

        // Animate all segments on composition
        segmentDrawingAnimRatio.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = if (animated) animationDurationMillis else 0))
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        val canvasSize = min(constraints.maxWidth, constraints.maxHeight)
        val canvasSizeDp = with(density) { canvasSize.toDp() }

        if (segmentsStartAngles.isNotEmpty()) {
            Canvas(
                modifier = Modifier
                    .size(canvasSizeDp)
                    .padding(Size.Padding.Default)
                    .pointerInput(segments) {
                        detectTapGestures { offset ->
                            val clickedAngle = computeAngleFromPointerInput(
                                width = canvasSize.toFloat(),
                                height = canvasSize.toFloat(),
                                pointerX = offset.x,
                                pointerY = offset.y,
                                referenceAngle = startAngleFromOrigin,
                            )

                            segmentsEndAngles.forEachIndexed { index, angle ->
                                if (clickedAngle <= angle) {
                                    // Only detect click if the segment is enabled
                                    if (segments[index].enabled) {
                                        onSegmentClicked?.invoke(segments[index])

                                        // Disable the current segment if the user clicked on the same one
                                        clickedSegmentIndex = when {
                                            clickedSegmentIndex == index -> NO_SELECTED_SEGMENT
                                            else -> index
                                        }
                                    }

                                    return@detectTapGestures
                                }
                            }
                        }
                    }
            ) {
                segments.forEachIndexed { index, segment ->
                    val isSelected = clickedSegmentIndex == index

                    drawSegment(
                        startAngle = segmentsStartAngles[index] + startAngleFromOrigin,
                        sweepAngle = (segment.angle - sizes.angleBetweenSegments()) * segmentDrawingAnimRatio.value,
                        strokeWidth = if (isSelected) sizes.selectedStrokeWidth() else sizes.strokeWidth(),
                        progress = segment.progress,
                        baseColor = segment.baseColor,
                        backgroundColor = segment.backgroundColor,
                    )
                }
            }
        }
    }
}

/**
 * Draws a single segment of the donut chart. A segment is composed of two arcs, one of top of the other.
 */
private fun DrawScope.drawSegment(
    startAngle: Degrees,
    sweepAngle: Degrees,
    @FloatRange(from = 0.0, to = 1.0) progress: Float = 1f,
    strokeWidth: Dp,
    baseColor: Color,
    backgroundColor: Color,
) {
    // Background arc
    drawArc(
        color = backgroundColor,
        startAngle = startAngle.value.toFloat(),
        sweepAngle = sweepAngle.value.toFloat(),
        useCenter = false,
        style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
    )

    // Foreground arc
    drawArc(
        color = baseColor,
        startAngle = startAngle.value.toFloat(),
        sweepAngle = (sweepAngle * progress).value.toFloat(),
        useCenter = false,
        style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
    )
}

private fun computeAngleFromPointerInput(
    width: Float,
    height: Float,
    pointerX: Float,
    pointerY: Float,
    referenceAngle: Degrees,
): Degrees {
    val x = pointerX - (width / 2)
    val y = pointerY - (height / 2)
    val angleRadians = atan2(y.toDouble(), x.toDouble()).radians - referenceAngle.toRadians()

    var angleDegrees = angleRadians.toDegrees()
    while (angleDegrees < 0) angleDegrees += 360

    return angleDegrees
}

object DonutChartDefault {

    fun chartSizes(
        strokeWidth: Dp = 8.dp,
        selectedStrokeWidth: Dp = 16.dp,
        angleBetweenSegments: Degrees = 8f.degrees,
    ): DonutChartSizes = DonutChartSizes(
        strokeWidth = strokeWidth,
        selectedStrokeWidth = selectedStrokeWidth,
        angleBetweenSegments = angleBetweenSegments
    )
}

@Immutable
class DonutChartSizes internal constructor(
    private val strokeWidth: Dp,
    private val selectedStrokeWidth: Dp,
    private val angleBetweenSegments: Degrees,
) {
    internal fun strokeWidth(): Dp = strokeWidth

    internal fun selectedStrokeWidth(): Dp = selectedStrokeWidth

    internal fun angleBetweenSegments(): Degrees = angleBetweenSegments
}
