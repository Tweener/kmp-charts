package com.tweener.charts.type.line.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultCap
import androidx.compose.ui.unit.Dp
import com.tweener.charts.model.StrokeStyle
import com.tweener.charts.model.StrokeStyle.Companion.DefaultStyle
import com.tweener.charts.type.line.LineChartDefaults
import com.tweener.charts.type.line.model.LineType.Companion.DefaultType

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

data class Line(
    val id: String,
    val plottedPoints: List<PlottedPoint>,
    val color: Color,
    val fillColorAlpha: Float = LineChartDefaults.LineGradientFillAlpha,
    val type: LineType = DefaultType,
    val strokeWidth: Dp = LineChartDefaults.LineStrokeWidth,
    val strokeCap: StrokeCap = DefaultCap,
    val strokeStyle: StrokeStyle = DefaultStyle,
)
