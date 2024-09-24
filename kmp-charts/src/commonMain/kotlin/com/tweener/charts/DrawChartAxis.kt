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
import com.tweener.charts.model.ChartColors
import com.tweener.charts.model.ChartSizes
import com.tweener.charts.model.StrokeStyle
import com.tweener.charts.model.XAxis
import com.tweener.charts.model.XAxisValue
import com.tweener.charts.model.YAxis
import com.tweener.charts.model.YAxisValue

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

internal fun <X, Y> DrawScope.drawAxes(
    textMeasurer: TextMeasurer,
    xAxis: XAxis<X>,
    yAxis: YAxis<Y>,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
    showXAxis: Boolean = true,
    showYAxis: Boolean = true,
) {
    val xAxisValueHeight = computeXValueHeight(textMeasurer, textStyle = textStyle, xAxis.values)
    val yAxisXOffset = computeYValueMaxWidth(textMeasurer, textStyle = textStyle, yAxis.values) + sizes.axisValuesPadding().toPx()
    val yAxisEndYOffset = size.height - xAxisValueHeight - sizes.axisValuesPadding().toPx()

    if (showXAxis) {
        drawXAxis(textMeasurer = textMeasurer, xAxis = xAxis, yOffset = yAxisEndYOffset, startXOffset = yAxisXOffset, textStyle = textStyle, colors = colors, sizes = sizes)
    }

    if (showYAxis) {
        drawYAxis(textMeasurer = textMeasurer, yAxis = yAxis, xOffset = yAxisXOffset, endYOffset = yAxisEndYOffset, textStyle = textStyle, colors = colors, sizes = sizes)
    }
}

internal fun <X> DrawScope.drawXAxis(
    textMeasurer: TextMeasurer,
    xAxis: XAxis<X>,
    yOffset: Float,
    startXOffset: Float,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
) {
    drawAxisLine(
        color = Color.Green,
        start = Offset(startXOffset, yOffset),
        end = Offset(size.width - startXOffset / 2, yOffset),
        strokeWidth = sizes.axisStrokeWidth(),
        strokeStyle = xAxis.strokeStyle,
        dashOn = sizes.axisDashOn(),
        dashOff = sizes.axisDashOff(),
    )

    val axisWidth = size.width - startXOffset / 2 - startXOffset
    val gapBetweenValues = if (xAxis.values.size > 1) axisWidth / (xAxis.values.size - 1) else 0f

    var startXValueOffset = startXOffset
    xAxis.values.forEach { xValue ->
        val valueWidth = textMeasurer.measure(text = AnnotatedString(xValue.name), style = textStyle).size.width.toFloat()
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

internal fun <Y> DrawScope.drawYAxis(
    textMeasurer: TextMeasurer,
    yAxis: YAxis<Y>,
    xOffset: Float,
    endYOffset: Float,
    textStyle: TextStyle,
    colors: ChartColors,
    sizes: ChartSizes,
) {
    val valueHeight = textMeasurer.measure(text = AnnotatedString(yAxis.values.first().name), style = textStyle).size.height.toFloat()

    drawAxisLine(
        color = Color.Red,
        start = Offset(xOffset, endYOffset),
        end = Offset(xOffset, valueHeight / 2),
        strokeWidth = sizes.axisStrokeWidth(),
        strokeStyle = yAxis.strokeStyle,
        dashOn = sizes.axisDashOn(),
        dashOff = sizes.axisDashOff(),
    )

    val axisHeight = endYOffset - valueHeight / 2
    val gapBetweenValues = if (yAxis.values.size > 1) axisHeight / (yAxis.values.size - 1) else 0f

    var startYValueOffset = endYOffset - valueHeight / 2
    yAxis.values.forEach { yValue ->
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
 * Computes the maximum width from the list of values to display on the Y axis.
 */
private fun <Y> computeYValueMaxWidth(textMeasurer: TextMeasurer, textStyle: TextStyle, yAxisValues: List<YAxisValue<Y>>): Float {
    var maxYValueWidth = 0
    yAxisValues.forEach { yValue ->
        val yValueWidth = textMeasurer.measure(text = AnnotatedString(yValue.name), style = textStyle).size.width
        if (yValueWidth > maxYValueWidth) maxYValueWidth = yValueWidth
    }

    return maxYValueWidth.toFloat()
}

/**
 * Computes the height for the values to display on the X axis.
 */
private fun <X> computeXValueHeight(textMeasurer: TextMeasurer, textStyle: TextStyle, xAxisValues: List<XAxisValue<X>>): Float =
    textMeasurer.measure(text = AnnotatedString(xAxisValues.first().name), style = textStyle).size.height.toFloat()
