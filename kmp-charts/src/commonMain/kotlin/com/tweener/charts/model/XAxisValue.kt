package com.tweener.charts.model

import com.tweener.charts.type.line.model.PointX

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
data class XAxisValue<X>(
    val x: PointX<X>,
    val name: String,
)
