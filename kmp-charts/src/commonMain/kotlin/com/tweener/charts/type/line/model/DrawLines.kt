package com.tweener.charts.type.line.model

/**
 * @author Vivien Mahe
 * @since 25/09/2024
 */

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.tweener.charts.model.Axis
import com.tweener.charts.model.GridOffsets

internal fun DrawScope.drawLines(
    lines: List<Line>,
    gridOffsets: GridOffsets,
    xAxis: Axis,
    yAxis: Axis,
    ratioAnimatable: Animatable<Float, AnimationVector1D>,
) {
    val minXValue = xAxis.values.minByOrNull { it.value }!!.value
    val maxXValue = xAxis.values.maxByOrNull { it.value }!!.value
    val minYValue = yAxis.values.minByOrNull { it.value }!!.value
    val maxYValue = yAxis.values.maxByOrNull { it.value }!!.value

    lines.forEach { line ->
        val points = line.plottedPoints.map { point ->
            val percentX = (point.values.x - minXValue) * 100 / (maxXValue - minXValue)
            val pointXOffset = percentX * (gridOffsets.bottomEndCorner.x - gridOffsets.bottomStartCorner.x) / 100

            val percentY = (point.values.y - minYValue) * 100 / (maxYValue - minYValue)
            val pointYOffset = percentY * (gridOffsets.bottomStartCorner.y - gridOffsets.topStartCorner.y) / 100

            Offset(
                x = pointXOffset.toFloat() + gridOffsets.topStartCorner.x,
                y = gridOffsets.bottomStartCorner.y - pointYOffset.toFloat() * ratioAnimatable.value,
            )
        }

        drawPoints(
            points = points,
            pointMode = PointMode.Polygon,
            color = line.color,
            strokeWidth = line.strokeWidth.toPx(),
        )
    }
}
