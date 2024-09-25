package com.tweener.charts.type.line.model

import kotlin.jvm.JvmInline

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */

data class PointCoordinates<X, Y>(
    val x: X,
    val y: Y,
)
