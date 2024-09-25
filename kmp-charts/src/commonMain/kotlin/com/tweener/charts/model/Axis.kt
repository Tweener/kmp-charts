package com.tweener.charts.model

import com.tweener.charts.model.StrokeStyle.Companion.DefaultStyle

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
data class Axis(
    val values: List<AxisValue>,
    val axisStrokeStyle: StrokeStyle = DefaultStyle,
    val gridStrokeStyle: StrokeStyle = DefaultStyle,
)
