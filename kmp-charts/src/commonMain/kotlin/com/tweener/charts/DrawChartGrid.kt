package com.tweener.charts

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tweener.charts._internal.kotlinextension.computeValueMaxHeight
import com.tweener.charts._internal.kotlinextension.computeValueWidth
import com.tweener.charts.model.Axis
import com.tweener.charts.model.ChartColors
import com.tweener.charts.model.ChartSizes
import com.tweener.charts.model.GridOffsets
import com.tweener.charts.model.GridVisibility
import com.tweener.charts.model.StrokeStyle

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

internal fun DrawScope.drawGrid(
    textMeasurer: TextMeasurer,
    gridOffsets: GridOffsets,
    xAxis: Axis,
    yAxis: Axis,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
    gridVisibility: GridVisibility,
) {
    if (xAxis.values.size > 1 && yAxis.values.size > 1) {
        drawXGrid(
            textMeasurer = textMeasurer,
            gridOffsets = gridOffsets,
            xAxis = xAxis,
            textStyle = textStyle,
            colors = colors,
            sizes = sizes,
            gridVisibility = gridVisibility,
        )

        drawYGrid(
            textMeasurer = textMeasurer,
            gridOffsets = gridOffsets,
            yAxis = yAxis,
            textStyle = textStyle,
            colors = colors,
            sizes = sizes,
            gridVisibility = gridVisibility,
        )
    }
}

private fun DrawScope.drawXGrid(
    textMeasurer: TextMeasurer,
    gridOffsets: GridOffsets,
    xAxis: Axis,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
    gridVisibility: GridVisibility,
) {
    val axisWidth = gridOffsets.topEndCorner.x - gridOffsets.topStartCorner.x
    val gapBetweenValues = if (xAxis.values.size > 1) axisWidth / (xAxis.values.size - 1) else 0f

    // Draw X axis
    if (gridVisibility.showXAxis()) {
        drawAxisLine(
            color = colors.yAxisValues(),
            start = gridOffsets.bottomStartCorner,
            end = gridOffsets.bottomEndCorner,
            strokeWidth = sizes.axisStrokeWidth(),
            strokeStyle = xAxis.axisStrokeStyle,
            dashOn = sizes.axisDashOn(),
            dashOff = sizes.axisDashOff(),
        )
    }

    var startXValueOffset = gridOffsets.topStartCorner.x
    xAxis.values.forEachIndexed { index, xValue ->
        val valueWidth = xValue.computeValueWidth(textMeasurer = textMeasurer, textStyle = textStyle)

        if (gridVisibility.showXGrid()) {
            // Draw vertical grid line matching the value on X axis, except for the first value (which is the Y axis)
            if (index in 1..<xAxis.values.size) {
                drawAxisLine(
                    color = colors.xAxisGrid(),
                    start = Offset(startXValueOffset, gridOffsets.bottomStartCorner.y),
                    end = Offset(startXValueOffset, gridOffsets.topStartCorner.y),
                    strokeWidth = sizes.axisStrokeWidth(),
                    strokeStyle = xAxis.gridStrokeStyle,
                    dashOn = sizes.axisDashOn(),
                    dashOff = sizes.axisDashOff(),
                )
            }
        }

        // Draw values on X axis (for the first and last value, make them fit inside the axis)
        when {
            index == xAxis.values.size - 1 -> startXValueOffset -= valueWidth
            index > 0 -> startXValueOffset -= valueWidth / 2
        }

        drawText(
            textMeasurer = textMeasurer,
            text = xValue.name,
            style = textStyle.copy(color = colors.xAxisValues()),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            topLeft = Offset(
                x = startXValueOffset.coerceAtMost(size.width),
                y = gridOffsets.bottomStartCorner.y + sizes.axisXValuesPadding().toPx(),
            )
        )

        when {
            index == xAxis.values.size - 1 -> startXValueOffset += valueWidth
            index > 0 -> startXValueOffset += valueWidth / 2
        }

        startXValueOffset += gapBetweenValues
    }
}

private fun DrawScope.drawYGrid(
    textMeasurer: TextMeasurer,
    gridOffsets: GridOffsets,
    yAxis: Axis,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
    gridVisibility: GridVisibility,
) {
    val valueHeight = yAxis.computeValueMaxHeight(textMeasurer = textMeasurer, textStyle = textStyle)
    val axisHeight = gridOffsets.bottomStartCorner.y - gridOffsets.topStartCorner.y
    val gapBetweenValues = if (yAxis.values.size > 1) axisHeight / (yAxis.values.size - 1) else 0f

    // Draw Y axis
    if (gridVisibility.showYAxis()) {
        drawAxisLine(
            color = colors.yAxisValues(),
            start = gridOffsets.bottomStartCorner,
            end = gridOffsets.topStartCorner,
            strokeWidth = sizes.axisStrokeWidth(),
            strokeStyle = yAxis.axisStrokeStyle,
            dashOn = sizes.axisDashOn(),
            dashOff = sizes.axisDashOff(),
        )
    }

    var startYValueOffset = gridOffsets.bottomStartCorner.y - valueHeight / 2
    yAxis.values.forEachIndexed { index, yValue ->
        if (gridVisibility.showYGrid()) {
            // Draw horizontal grid line matching the value on Y axis, except for the first value (which is the X axis)
            if (index in 1..<yAxis.values.size) {
                drawAxisLine(
                    color = colors.yAxisGrid(),
                    start = Offset(gridOffsets.topStartCorner.x, startYValueOffset + valueHeight / 2),
                    end = Offset(gridOffsets.topEndCorner.x, startYValueOffset + valueHeight / 2),
                    strokeWidth = sizes.axisStrokeWidth(),
                    strokeStyle = yAxis.gridStrokeStyle,
                    dashOn = sizes.axisDashOn(),
                    dashOff = sizes.axisDashOff(),
                )
            }
        }

        // Draw values on Y axis
        drawText(
            textMeasurer = textMeasurer,
            text = yValue.name,
            style = textStyle.copy(color = colors.yAxisValues()),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            topLeft = Offset(
                x = 0f,
                y = startYValueOffset.coerceAtLeast(0f),
            )
        )

        startYValueOffset -= gapBetweenValues
    }
}

private fun DrawScope.drawAxisLine(
    color: Color,
    start: Offset,
    end: Offset,
    strokeWidth: Dp,
    strokeStyle: StrokeStyle,
    dashOn: Dp = 0.dp,
    dashOff: Dp = 0.dp,
) {
    val pathEffect = if (strokeStyle == StrokeStyle.Dashed) {
        PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashOn.toPx(), dashOff.toPx()),
            phase = 0f,
        )
    } else null

    drawLine(
        color = color,
        start = start,
        end = end,
        strokeWidth = strokeWidth.toPx(),
        pathEffect = pathEffect,
    )
}
