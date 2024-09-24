package com.tweener.charts.type.line.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke.Companion.DefaultCap
import com.tweener.charts.model.StrokeStyle
import com.tweener.charts.model.StrokeStyle.Companion.DefaultStyle
import com.tweener.charts.type.line.model.LineType.Companion.DefaultType

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

data class Line<X, Y>(
    val id: String,
    val plottedPoints: List<PlottedPoint<X, Y>>,
    val color: Color,
    val type: LineType = DefaultType,
    val strokeWidth: Float = Stroke.HairlineWidth,
    val strokeCap: StrokeCap = DefaultCap,
    val strokeStyle: StrokeStyle = DefaultStyle,
)
