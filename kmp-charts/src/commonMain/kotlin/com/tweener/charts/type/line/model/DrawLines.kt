package com.tweener.charts.type.line.model

/**
 * @author Vivien Mahe
 * @since 25/09/2024
 */

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
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
        // Compute points coordinates
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

        // Draw line
        val path = when (line.type) {
            LineType.Straight -> drawStraightLine(line = line, points = points, gridOffsets = gridOffsets)
            LineType.Curved -> drawCurvedLine(line = line, points = points, gridOffsets = gridOffsets)
            else -> null
        }

        // Draw gradient fill below the line
        path?.let {
            drawPath(
                path = it,
                brush = Brush.verticalGradient(listOf(line.color.copy(alpha = line.fillColorAlpha), Color.Transparent)),
                style = Fill,
            )

        }
    }
}

private fun DrawScope.drawStraightLine(
    line: Line,
    points: List<Offset>,
    gridOffsets: GridOffsets,
): Path {
    // Compute path
    val path = Path()

    points.forEachIndexed { index, point ->
        when (index) {
            0 -> path.moveTo(point.x, point.y)
            else -> path.lineTo(point.x, point.y)
        }
    }

    // Draw line segments
    drawPath(
        path = path,
        color = line.color,
        style = Stroke(width = line.strokeWidth.toPx()),
    )

    // Draw fill brush
    path.lineTo(gridOffsets.bottomEndCorner.x, gridOffsets.bottomEndCorner.y)
    path.lineTo(gridOffsets.bottomStartCorner.x, gridOffsets.bottomStartCorner.y)

    return path
}

private fun DrawScope.drawCurvedLine(
    line: Line,
    points: List<Offset>,
    gridOffsets: GridOffsets,
): Path {
    // Compute path
    val path = Path()
    var previousPoint: Offset? = null

    points.forEachIndexed { index, point ->
        previousPoint?.let { buildCurveLine(path = path, startPoint = it, endPoint = point, useQuadratic = points.size <= 2) }
        previousPoint = point
        if (index == 0) path.moveTo(point.x, point.y)
    }

    // Draw curved line
    drawPath(
        path = path,
        color = line.color,
        style = Stroke(width = line.strokeWidth.toPx()),
    )

    // Draw fill brush
    path.lineTo(gridOffsets.bottomEndCorner.x, gridOffsets.bottomEndCorner.y)
    path.lineTo(gridOffsets.bottomStartCorner.x, gridOffsets.bottomStartCorner.y)

    return path
}

private fun buildCurveLine(path: Path, startPoint: Offset, endPoint: Offset, useQuadratic: Boolean = false) {
    val firstControlPoint = Offset(
        x = startPoint.x + (endPoint.x - startPoint.x) / 2,
        y = startPoint.y,
    )

    when (useQuadratic) {
        true -> {
            path.quadraticTo(
                x1 = firstControlPoint.x,
                y1 = firstControlPoint.y,
                x2 = endPoint.x,
                y2 = endPoint.y,
            )
        }

        false -> {
            val secondControlPoint = Offset(
                x = startPoint.x + (endPoint.x - startPoint.x) / 2,
                y = endPoint.y,
            )

            path.cubicTo(
                x1 = firstControlPoint.x,
                y1 = firstControlPoint.y,
                x2 = secondControlPoint.x,
                y2 = secondControlPoint.y,
                x3 = endPoint.x,
                y3 = endPoint.y,
            )
        }
    }
}
