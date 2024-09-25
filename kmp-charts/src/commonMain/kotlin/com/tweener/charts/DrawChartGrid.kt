package com.tweener.charts

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.tweener.charts.model.Axis
import com.tweener.charts.model.ChartColors
import com.tweener.charts.model.ChartSizes
import com.tweener.charts.model.GridVisibility
import com.tweener.charts.model.StrokeStyle

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

internal fun <X, Y> DrawScope.drawGrid(
    textMeasurer: TextMeasurer,
    xAxis: Axis<X>,
    yAxis: Axis<Y>,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
    gridVisibility: GridVisibility,
) {
    if (xAxis.values.size > 1 && yAxis.values.size > 1) {
        val xAxisValueHeight = xAxis.values.map { it.name }.computeValueMaxHeight(textMeasurer, textStyle = textStyle)
        val yAxisXOffset = yAxis.values.map { it.name }.computeValueMaxWidth(textMeasurer, textStyle = textStyle) + sizes.axisValuesPadding().toPx()
        val yAxisEndYOffset = size.height - xAxisValueHeight - sizes.axisValuesPadding().toPx()

        drawXGrid(
            textMeasurer = textMeasurer,
            xAxis = xAxis,
            yOffset = yAxisEndYOffset,
            startXOffset = yAxisXOffset,
            textStyle = textStyle,
            colors = colors,
            sizes = sizes,
            gridVisibility = gridVisibility,
        )

        drawYGrid(
            textMeasurer = textMeasurer,
            yAxis = yAxis,
            xOffset = yAxisXOffset,
            endYOffset = yAxisEndYOffset,
            textStyle = textStyle,
            colors = colors,
            sizes = sizes,
            gridVisibility = gridVisibility,
        )
    }
}

private fun <X> DrawScope.drawXGrid(
    textMeasurer: TextMeasurer,
    xAxis: Axis<X>,
    yOffset: Float,
    startXOffset: Float,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
    gridVisibility: GridVisibility,
) {
    val valueHeight = textMeasurer.measure(text = AnnotatedString(xAxis.values.first().name), style = textStyle).size.height.toFloat()

    // Draw X axis
    if (gridVisibility.showXAxis()) {
        drawAxisLine(
            color = colors.yAxisValues(),
            start = Offset(startXOffset, yOffset),
            end = Offset(size.width - startXOffset / 2, yOffset),
            strokeWidth = sizes.axisStrokeWidth(),
            strokeStyle = xAxis.axisStrokeStyle,
            dashOn = sizes.axisDashOn(),
            dashOff = sizes.axisDashOff(),
        )
    }

    val axisWidth = size.width - startXOffset / 2 - startXOffset
    val gapBetweenValues = if (xAxis.values.size > 1) axisWidth / (xAxis.values.size - 1) else 0f

    var startXValueOffset = startXOffset
    xAxis.values.forEachIndexed { index, xValue ->
        val valueWidth = textMeasurer.measure(text = AnnotatedString(xValue.name), style = textStyle).size.width.toFloat()

        if (gridVisibility.showXGrid()) {
            // Draw vertical grid line matching the value on X axis
            if (index in 1..<xAxis.values.size) {
                drawAxisLine(
                    color = colors.xAxisGrid(),
                    start = Offset(startXValueOffset, yOffset),
                    end = Offset(startXValueOffset, valueHeight / 2),
                    strokeWidth = sizes.axisStrokeWidth(),
                    strokeStyle = xAxis.gridStrokeStyle,
                    dashOn = sizes.axisDashOn(),
                    dashOff = sizes.axisDashOff(),
                )
            }
        }

        // Draw value on X axis
        startXValueOffset -= valueWidth / 2

        drawText(
            textMeasurer = textMeasurer,
            text = xValue.name,
            style = textStyle.copy(color = colors.xAxisValues()),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            topLeft = Offset(
                x = startXValueOffset.coerceAtMost(size.width),
                y = yOffset + sizes.axisValuesPadding().toPx(),
            )
        )

        startXValueOffset += gapBetweenValues + valueWidth / 2
    }
}

private fun <Y> DrawScope.drawYGrid(
    textMeasurer: TextMeasurer,
    yAxis: Axis<Y>,
    xOffset: Float,
    endYOffset: Float,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
    gridVisibility: GridVisibility,
) {
    val valueHeight = textMeasurer.measure(text = AnnotatedString(yAxis.values.first().name), style = textStyle).size.height.toFloat()
    val axisWidth = size.width - xOffset / 2

    // Draw Y axis
    if (gridVisibility.showYAxis()) {
        drawAxisLine(
            color = colors.yAxisValues(),
            start = Offset(xOffset, endYOffset),
            end = Offset(xOffset, valueHeight / 2),
            strokeWidth = sizes.axisStrokeWidth(),
            strokeStyle = yAxis.axisStrokeStyle,
            dashOn = sizes.axisDashOn(),
            dashOff = sizes.axisDashOff(),
        )
    }

    val axisHeight = endYOffset - valueHeight / 2
    val gapBetweenValues = if (yAxis.values.size > 1) axisHeight / (yAxis.values.size - 1) else 0f

    var startYValueOffset = endYOffset - valueHeight / 2
    yAxis.values.forEach { yValue ->
        if (gridVisibility.showYGrid()) {
            // Draw horizontal grid line matching the value on Y axis
            drawAxisLine(
                color = colors.yAxisValues(),
                start = Offset(xOffset, startYValueOffset + valueHeight / 2),
                end = Offset(axisWidth, startYValueOffset + valueHeight / 2),
                strokeWidth = sizes.axisStrokeWidth(),
                strokeStyle = yAxis.gridStrokeStyle,
                dashOn = sizes.axisDashOn(),
                dashOff = sizes.axisDashOff(),
            )
        }

        // Draw value on Y axis
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
    dashOn: Dp,
    dashOff: Dp,
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

/**
 * Computes the maximum width from the list of values.
 */
fun List<String>.computeValueMaxWidth(textMeasurer: TextMeasurer, textStyle: TextStyle): Float {
    var maxValueWidth = 0
    forEach { name ->
        val valueWidth = textMeasurer.measure(text = AnnotatedString(name), style = textStyle).size.width
        if (valueWidth > maxValueWidth) maxValueWidth = valueWidth
    }

    return maxValueWidth.toFloat()
}

/**
 * Computes the maximum height from the list of values.
 */
fun List<String>.computeValueMaxHeight(textMeasurer: TextMeasurer, textStyle: TextStyle): Float =
    textMeasurer.measure(text = AnnotatedString(first()), style = textStyle).size.height.toFloat()
