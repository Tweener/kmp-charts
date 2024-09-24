package com.tweener.charts.type.line

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tweener.charts.drawAxes
import com.tweener.charts.model.ChartSizes
import com.tweener.charts.model.XAxis
import com.tweener.charts.model.YAxis
import com.tweener.charts.type.line.model.Line

@Composable
fun <X, Y> LineChart(
    lines: List<Line<X, Y>>,
    xAxis: XAxis<X>,
    yAxis: YAxis<Y>,
    modifier: Modifier = Modifier,
    gridVisibility: GridVisibility = LineChartDefaults.gridVisibility(),
    sizes: LineChartSizes = LineChartDefaults.chartSizes(),
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = modifier.fillMaxSize(),
    ) {
        drawAxes(
            textMeasurer = textMeasurer,
            xAxis = xAxis,
            yAxis = yAxis,
            sizes = sizes,
            showXAxis = gridVisibility.showXAxis(),
            showYAxis = gridVisibility.showYAxis(),
        )
    }
}


object LineChartDefaults {

    private const val ShowXAxis = true
    private const val ShowYAxis = true

    private val AxisStrokeWidth = 4.dp
    private val AxisDashOn = 10.dp
    private val AxisDashOff = 10.dp

    fun gridVisibility(
        showXAxis: Boolean = ShowXAxis,
        showYAxis: Boolean = ShowYAxis,
    ): GridVisibility = GridVisibility(
        showXAxis = showXAxis,
        showYAxis = showYAxis,
    )

    fun chartSizes(
        axisStrokeWidth: Dp = AxisStrokeWidth,
        axisDashOn: Dp = AxisDashOn,
        axisDashOff: Dp = AxisDashOff,
    ): LineChartSizes = LineChartSizes(
        axisStrokeWidth = axisStrokeWidth,
        axisDashOn = axisDashOn,
        axisDashOff = axisDashOff,
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
) : ChartSizes(axisStrokeWidth = axisStrokeWidth, axisDashOn = axisDashOn, axisDashOff = axisDashOff) {
}
