package com.tweener.charts.model

import com.tweener.charts.model.StrokeStyle.Companion.DefaultStyle

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
data class XAxis<X>(
    val values: List<XAxisValue<X>>,
    val strokeStyle: StrokeStyle = DefaultStyle,
)
