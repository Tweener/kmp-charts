package com.tweener.charts.type.line

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.tweener.czan.preview.CzanThemePreview
import com.tweener.czan.preview.UiModePreviews
import com.tweener.czan.theme.Size

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

@UiModePreviews
@Composable
private fun LineChartPreview() {
    val line1 = Line(
        id = "line1",
        plottedPoints = listOf(
            PlottedPoint(id = "p1", values = PointValues(x = 2020.0, y = 1000.0)),
            PlottedPoint(id = "p2", values = PointValues(x = 2021.0, y = 4389.0)),
            PlottedPoint(id = "p3", values = PointValues(x = 2022.0, y = 9817.0)),
            PlottedPoint(id = "p4", values = PointValues(x = 2023.0, y = 16489.0)),
            PlottedPoint(id = "p5", values = PointValues(x = 2024.0, y = 32896.0)),
        ),
        color = Color.Red,
        type = LineType.Straight,
        strokeWidth = 4.dp,
    )

    CzanThemePreview {
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            lines = listOf(line1),
            xAxis = Axis(
                values = listOf(
                    AxisValue(value = 2020.0, name = "2020"),
                    AxisValue(value = 2021.0, name = "2021"),
                    AxisValue(value = 2022.0, name = "2022"),
                    AxisValue(value = 2023.0, name = "2023"),
                    AxisValue(value = 2024.0, name = "2024"),
                ),
                axisStrokeStyle = StrokeStyle.Solid,
                gridStrokeStyle = StrokeStyle.Dashed,
            ),
            yAxis = Axis(
                values = listOf(
                    AxisValue(value = 0.0, name = "0 €"),
                    AxisValue(value = 10000.0, name = "10 000 €"),
                    AxisValue(value = 20000.0, name = "20 000 €"),
                    AxisValue(value = 30000.0, name = "30 000 €"),
                    AxisValue(value = 40000.0, name = "40 000 €"),
                ),
                axisStrokeStyle = StrokeStyle.Solid,
                gridStrokeStyle = StrokeStyle.Dashed,
            ),
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
                axisValuesPadding = Size.Padding.Small,
            )
        )
    }
}
