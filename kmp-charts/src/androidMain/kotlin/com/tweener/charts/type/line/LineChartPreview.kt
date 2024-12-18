package com.tweener.charts.type.line

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tweener.charts.model.Axis
import com.tweener.charts.model.AxisValue
import com.tweener.charts.model.StrokeStyle
import com.tweener.charts.type.line.model.Line
import com.tweener.charts.type.line.model.LineType
import com.tweener.charts.type.line.model.PlottedPoint
import com.tweener.charts.type.line.model.PointValues
import com.tweener.czan._internal.safeLet
import com.tweener.czan.designsystem.atom.button.Button
import com.tweener.czan.preview.CzanThemePreview
import com.tweener.czan.preview.UiModePreviews
import com.tweener.czan.theme.Size
import kotlin.random.Random

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

private enum class PeriodType {
    ONE_MONTH,
    SIX_MONTHS,
    ONE_YEAR;
}

@UiModePreviews
@Composable
private fun LineChartPreview() {
    val lineColors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Magenta, Color.Gray)
    var periodType by remember { mutableStateOf(PeriodType.ONE_MONTH) }
    var lines by remember { mutableStateOf(listOf<Line>()) }
    var xAxis by remember { mutableStateOf<Axis?>(null) }
    var yAxis by remember { mutableStateOf<Axis?>(null) }

    val onAddNewLine: () -> Unit = {
        com.tweener.kmpkit.safeLet(xAxis, yAxis) { xAxis, yAxis ->
            val dataPoints = randomizePoints(xAxis = xAxis, yAxis = yAxis)

            val line = Line(
                id = lines.size.toString(),
                plottedPoints = dataPoints,
                color = lineColors[lines.size % lineColors.size],
                fillColorAlpha = 0.8f,
                type = LineType(lines.size % 2),
                strokeWidth = 2.dp,
            )

            lines = lines.toMutableList().apply { add(line) }
        }
    }

    val onRandomizeChart: () -> Unit = {
        com.tweener.kmpkit.safeLet(xAxis, yAxis) { xAxis, yAxis ->
            lines = lines.map { line ->
                line.copy(plottedPoints = randomizePoints(xAxis = xAxis, yAxis = yAxis))
            }
        }
    }

    val onChangeDateRange: (PeriodType) -> Unit = { newPeriodType ->
        val xValues = when (newPeriodType) {
            PeriodType.ONE_MONTH -> listOf(
                AxisValue(value = 1.0, name = "22/05"),
                AxisValue(value = 2.0, name = "29/05"),
                AxisValue(value = 3.0, name = "05/06"),
                AxisValue(value = 4.0, name = "12/06"),
                AxisValue(value = 5.0, name = "19/06"),
                AxisValue(value = 6.0, name = "22/06"),
            )

            PeriodType.SIX_MONTHS -> listOf(
                AxisValue(value = 1.0, name = "FEB"),
                AxisValue(value = 2.0, name = "MAR"),
                AxisValue(value = 3.0, name = "APR"),
                AxisValue(value = 4.0, name = "JUN"),
                AxisValue(value = 5.0, name = "JUL"),
                AxisValue(value = 6.0, name = "AUG"),
            )

            PeriodType.ONE_YEAR -> listOf(
                AxisValue(value = 1.0, name = "FEB"),
                AxisValue(value = 2.0, name = "MAR"),
                AxisValue(value = 3.0, name = "APR"),
                AxisValue(value = 4.0, name = "MAY"),
                AxisValue(value = 5.0, name = "JUN"),
                AxisValue(value = 6.0, name = "JUL"),
                AxisValue(value = 7.0, name = "AUG"),
                AxisValue(value = 8.0, name = "SEP"),
                AxisValue(value = 9.0, name = "OCT"),
                AxisValue(value = 10.0, name = "NOV"),
                AxisValue(value = 11.0, name = "DEC"),
                AxisValue(value = 12.0, name = "JAN"),
            )
        }

        val yValues = when (newPeriodType) {
            PeriodType.ONE_MONTH -> listOf(
                AxisValue(value = 0.0, name = "0 €"),
                AxisValue(value = 1000.0, name = "1 000 €"),
                AxisValue(value = 2000.0, name = "2 000 €"),
                AxisValue(value = 3000.0, name = "3 000 €"),
                AxisValue(value = 4000.0, name = "4 000 €"),
            )

            PeriodType.SIX_MONTHS -> listOf(
                AxisValue(value = 0.0, name = "0 €"),
                AxisValue(value = 5000.0, name = "5 000 €"),
                AxisValue(value = 10000.0, name = "10 000 €"),
                AxisValue(value = 15000.0, name = "15 000 €"),
                AxisValue(value = 20000.0, name = "20 000 €"),
            )

            PeriodType.ONE_YEAR -> listOf(
                AxisValue(value = 0.0, name = "0 €"),
                AxisValue(value = 10000.0, name = "10 000 €"),
                AxisValue(value = 20000.0, name = "20 000 €"),
                AxisValue(value = 30000.0, name = "30 000 €"),
                AxisValue(value = 40000.0, name = "40 000 €"),
            )
        }

        xAxis = Axis(
            values = xValues,
            axisStrokeStyle = StrokeStyle.Solid,
            gridStrokeStyle = StrokeStyle.Dashed,
        )

        yAxis = Axis(
            values = yValues,
            axisStrokeStyle = StrokeStyle.Solid,
            gridStrokeStyle = StrokeStyle.Dashed,
        )

        onRandomizeChart()
    }

    LaunchedEffect(periodType) {
        onChangeDateRange(periodType)
    }

    CzanThemePreview {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Size.Padding.Default)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(Size.Padding.Small)) {
                Button(text = "1 month") { periodType = PeriodType.ONE_MONTH }
                Button(text = "6 months") { periodType = PeriodType.SIX_MONTHS }
                Button(text = "1 year") { periodType = PeriodType.ONE_YEAR }
            }

            Button(text = "Add new line") { onAddNewLine() }

            Button(text = "Randomize data points") { onRandomizeChart() }

            safeLet(xAxis, yAxis) { xAxis, yAxis ->
                LineChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    lines = lines,
                    xAxis = xAxis,
                    yAxis = yAxis,
                    textStyle = MaterialTheme.typography.labelLarge,
                    gridVisibility = LineChartDefaults.gridVisibility(
                        showXAxis = true,
                        showYAxis = true,
                    ),
                    colors = LineChartDefaults.chartColors(
                        xAxisValues = MaterialTheme.colorScheme.onBackground,
                        xAxisGrid = MaterialTheme.colorScheme.outline,
                        yAxisValues = MaterialTheme.colorScheme.onBackground,
                        yAxisGrid = MaterialTheme.colorScheme.outline,
                    ),
                    sizes = LineChartDefaults.chartSizes(
                        axisStrokeWidth = 1.dp,
                        axisDashOn = 8.dp,
                        axisDashOff = 8.dp,
                        axisXValuesPadding = Size.Padding.Small,
                        axisYValuesPadding = Size.Padding.ExtraSmall,
                    )
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        onAddNewLine()
    }
}

private fun randomizePoints(xAxis: Axis, yAxis: Axis): List<PlottedPoint> {
    val dataPoints = mutableListOf<PlottedPoint>()

    val lowestYValue = yAxis.values.minByOrNull { it.value }!!.value
    val highestYValue = yAxis.values.maxByOrNull { it.value }!!.value

    xAxis.values.forEach { xValue ->
        val x = xValue.value
        val y = Random.nextDouble(lowestYValue, highestYValue)

        dataPoints.add(PlottedPoint(id = "#$xValue", values = PointValues(x = x, y = y)))
    }

    return dataPoints
}
