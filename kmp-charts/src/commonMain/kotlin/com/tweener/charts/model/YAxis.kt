package com.tweener.charts.model

import com.tweener.charts.model.StrokeStyle.Companion.DefaultStyle

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
data class YAxis<Y>(
    val values: List<YAxisValue<Y>>,
    val strokeStyle: StrokeStyle = DefaultStyle,
)
