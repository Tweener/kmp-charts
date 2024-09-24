package com.tweener.charts.type.line

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tweener.charts.model.XAxis
import com.tweener.charts.model.XAxisValue
import com.tweener.charts.model.YAxis
import com.tweener.charts.model.YAxisValue
import com.tweener.charts.type.line.model.Line
import com.tweener.charts.type.line.model.LineType
import com.tweener.charts.type.line.model.PlottedPoint
import com.tweener.charts.type.line.model.PointCoordinates
import com.tweener.charts.type.line.model.PointX
import com.tweener.charts.type.line.model.PointY
import com.tweener.czan.preview.UiModePreviews

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
            PlottedPoint(id = "p1", coordinates = PointCoordinates(x = PointX(2020), y = PointY(1000.0))),
            PlottedPoint(id = "p2", coordinates = PointCoordinates(x = PointX(2021), y = PointY(4389.0))),
            PlottedPoint(id = "p3", coordinates = PointCoordinates(x = PointX(2022), y = PointY(9817.0))),
            PlottedPoint(id = "p4", coordinates = PointCoordinates(x = PointX(2023), y = PointY(16489.0))),
            PlottedPoint(id = "p5", coordinates = PointCoordinates(x = PointX(2024), y = PointY(32896.0))),
        ),
        color = Color.Red,
        type = LineType.Straight,
    )

    LineChart(
        modifier = Modifier.wrapContentSize(),
        lines = listOf(line1),
        xAxis = XAxis(
            values = listOf(
                XAxisValue(x = PointX(2020), name = "2020"),
                XAxisValue(x = PointX(2021), name = "2021"),
                XAxisValue(x = PointX(2022), name = "2022"),
                XAxisValue(x = PointX(2023), name = "2023"),
                XAxisValue(x = PointX(2024), name = "2024"),
            ),
            lineType = LineType.Straight,
        ),
        yAxis = YAxis(
            values = listOf(
                YAxisValue(y = PointY(0.0), name = "0 €"),
                YAxisValue(y = PointY(10000.0), name = "10 000 €"),
                YAxisValue(y = PointY(20000.0), name = "20 000 €"),
                YAxisValue(y = PointY(30000.0), name = "30 000 €"),
                YAxisValue(y = PointY(40000.0), name = "40 000 €"),
            ),
            lineType = LineType.Straight,
        ),
        gridVisibility = LineChartDefaults.gridVisibility(
            showXAxis = true,
            showYAxis = true,
        ),
    )
}
