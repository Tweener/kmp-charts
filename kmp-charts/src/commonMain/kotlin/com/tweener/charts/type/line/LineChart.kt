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
import com.tweener.charts.drawAxes
import com.tweener.charts.model.ChartColors
import com.tweener.charts.model.ChartSizes
import com.tweener.charts.model.XAxis
import com.tweener.charts.model.YAxis
import com.tweener.charts.type.line.model.Line
import com.tweener.czan.theme.Size

@Composable
fun <X, Y> LineChart(
    lines: List<Line<X, Y>>,
    xAxis: XAxis<X>,
    yAxis: YAxis<Y>,
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
        if (xAxis.values.size > 1 && yAxis.values.size > 1) {
            drawAxes(
                textMeasurer = textMeasurer,
                xAxis = xAxis,
                yAxis = yAxis,
                colors = colors,
                sizes = sizes,
                showXAxis = gridVisibility.showXAxis(),
                showYAxis = gridVisibility.showYAxis(),
                textStyle = textStyle,
            )
        }
    }
}


object LineChartDefaults {

    fun gridVisibility(
        showXAxis: Boolean = true,
        showYAxis: Boolean = true,
    ): GridVisibility = GridVisibility(
        showXAxis = showXAxis,
        showYAxis = showYAxis,
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
        yAxisValues: Color = MaterialTheme.colorScheme.onBackground,
    ): LineChartColors = LineChartColors(
        xAxisValues = xAxisValues,
        yAxisValues = yAxisValues,
    )
}

@Immutable
class GridVisibility internal constructor(
    private val showXAxis: Boolean,
    private val showYAxis: Boolean,
) {
    internal fun showXAxis(): Boolean = showXAxis

    internal fun showYAxis(): Boolean = showYAxis
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
    yAxisValues: Color,
) : ChartColors(xAxisValues = xAxisValues, yAxisValues = yAxisValues)
