package com.tweener.charts.type.line.model

data class PlottedPoint<X, Y>(
    val id: String,
    val coordinates: PointCoordinates<X, Y>,
)
