package com.tweener.charts.type.line

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tweener.charts._internal.kotlinextension.computeValueMaxHeight
import com.tweener.charts._internal.kotlinextension.computeValueMaxWidth
import com.tweener.charts.drawGrid
import com.tweener.charts.model.Axis
import com.tweener.charts.model.ChartColors
import com.tweener.charts.model.ChartSizes
import com.tweener.charts.model.GridVisibility
import com.tweener.charts.type.line.model.Line
import com.tweener.czan.theme.Size

@Composable
fun <X, Y> LineChart(
    lines: List<Line<X, Y>>,
    xAxis: Axis<X>,
    yAxis: Axis<Y>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    gridVisibility: GridVisibility = LineChartDefaults.gridVisibility(),
    colors: LineChartColors = LineChartDefaults.chartColors(),
    sizes: LineChartSizes = LineChartDefaults.chartSizes(),
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier.fillMaxSize(),
    ) {
        drawGrid(
            textMeasurer = textMeasurer,
            xAxis = xAxis,
            yAxis = yAxis,
            textStyle = textStyle,
            colors = colors,
            sizes = sizes,
            gridVisibility = gridVisibility,
        )

        val xAxisValueHeight = xAxis.computeValueMaxHeight(textMeasurer, textStyle = textStyle)
        val yAxisValueWidth = yAxis.computeValueMaxWidth(textMeasurer, textStyle = textStyle)
        val yAxisStartXOffset = yAxisValueWidth + sizes.axisValuesPadding().toPx()
        val yAxisEndYOffset = size.height - xAxisValueHeight - sizes.axisValuesPadding().toPx()

        lines.forEach { line ->
            line.plottedPoints.forEach { point ->

            }
        }
    }
}


object LineChartDefaults {

    fun gridVisibility(
        showXAxis: Boolean = true,
        showYAxis: Boolean = true,
        showXGrid: Boolean = true,
        showYGrid: Boolean = true,
    ): GridVisibility = GridVisibility(
        showXAxis = showXAxis,
        showYAxis = showYAxis,
        showXGrid = showXGrid,
        showYGrid = showYGrid,
    )

    fun chartSizes(
        axisStrokeWidth: Dp = 4.dp,
        axisDashOn: Dp = 10.dp,
        axisDashOff: Dp = 10.dp,
        axisValuesPadding: Dp = Size.Padding.Tiny,
    ): LineChartSizes = LineChartSizes(
        axisStrokeWidth = axisStrokeWidth,
        axisDashOn = axisDashOn,
        axisDashOff = axisDashOff,
        axisValuesPadding = axisValuesPadding,
    )

    @Composable
    fun chartColors(
        xAxisValues: Color = MaterialTheme.colorScheme.onBackground,
        xAxisGrid: Color = MaterialTheme.colorScheme.outline,
        yAxisValues: Color = MaterialTheme.colorScheme.onBackground,
        yAxisGrid: Color = MaterialTheme.colorScheme.outline,
    ): LineChartColors = LineChartColors(
        xAxisValues = xAxisValues,
        xAxisGrid = xAxisGrid,
        yAxisValues = yAxisValues,
        yAxisGrid = yAxisGrid,
    )
}

@Immutable
class LineChartSizes internal constructor(
    axisStrokeWidth: Dp,
    axisDashOn: Dp,
    axisDashOff: Dp,
    axisValuesPadding: Dp,
) : ChartSizes(axisStrokeWidth = axisStrokeWidth, axisDashOn = axisDashOn, axisDashOff = axisDashOff, axisValuesPadding = axisValuesPadding)

@Immutable
class LineChartColors internal constructor(
    xAxisValues: Color,
    xAxisGrid: Color,
    yAxisValues: Color,
    yAxisGrid: Color,
) : ChartColors(xAxisValues = xAxisValues, xAxisGrid = xAxisGrid, yAxisValues = yAxisValues, yAxisGrid = yAxisGrid)
