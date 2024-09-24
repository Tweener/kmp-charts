package com.tweener.charts.model

import com.tweener.charts.type.line.model.PointY

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
data class YAxisValue<Y>(
    val y: PointY<Y>,
    val name: String,
)
