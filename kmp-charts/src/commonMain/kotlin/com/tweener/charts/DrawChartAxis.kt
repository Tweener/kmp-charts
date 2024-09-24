package com.tweener.charts

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Dp
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
    sizes: ChartSizes,
    showXAxis: Boolean = true,
    showYAxis: Boolean = true,
) {
    val xAxisValueHeight = computeXValueHeight(textMeasurer, xAxis.values)
    val yAxisXOffset = computeYValueMaxWidth(textMeasurer, yAxis.values)
    val yAxisEndYOffset = size.height - xAxisValueHeight

    if (showXAxis) {
        drawXAxis(xAxis = xAxis, yOffset = yAxisEndYOffset, startXOffset = yAxisXOffset, sizes = sizes)
    }

    if (showYAxis) {
        drawYAxis(yAxis = yAxis, xOffset = yAxisXOffset, endYOffset = yAxisEndYOffset, sizes = sizes)
    }
}

internal fun <X> DrawScope.drawXAxis(
    xAxis: XAxis<X>,
    yOffset: Float,
    startXOffset: Float,
    sizes: ChartSizes,
) {
    drawAxisLine(
        color = Color.Green,
        start = Offset(startXOffset, yOffset),
        end = Offset(size.width, yOffset),
        strokeWidth = sizes.axisStrokeWidth(),
        strokeStyle = xAxis.strokeStyle,
        dashOn = sizes.axisDashOn(),
        dashOff = sizes.axisDashOff(),
    )
}

internal fun <Y> DrawScope.drawYAxis(
    yAxis: YAxis<Y>,
    xOffset: Float,
    endYOffset: Float,
    sizes: ChartSizes,
) {
    drawAxisLine(
        color = Color.Red,
        start = Offset(xOffset, endYOffset),
        end = Offset(xOffset, 0f),
        strokeWidth = sizes.axisStrokeWidth(),
        strokeStyle = yAxis.strokeStyle,
        dashOn = sizes.axisDashOn(),
        dashOff = sizes.axisDashOff(),
    )
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
private fun <Y> computeYValueMaxWidth(textMeasurer: TextMeasurer, yAxisValues: List<YAxisValue<Y>>): Float {
    var maxYValueWidth = 0
    yAxisValues.forEach { yValue ->
        val yValueWidth = textMeasurer.measure(text = AnnotatedString(yValue.name)).size.width
        if (yValueWidth > maxYValueWidth) maxYValueWidth = yValueWidth
    }

    return maxYValueWidth.toFloat()
}

/**
 * Computes the height for the values to display on the X axis.
 */
private fun <X> computeXValueHeight(textMeasurer: TextMeasurer, xAxisValues: List<XAxisValue<X>>): Float =
    textMeasurer.measure(text = AnnotatedString(xAxisValues.first().name)).size.height.toFloat()
