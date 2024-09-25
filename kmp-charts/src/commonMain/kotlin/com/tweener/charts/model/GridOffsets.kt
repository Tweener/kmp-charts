package com.tweener.charts.model

import androidx.compose.ui.geometry.Offset

/**
 * @author Vivien Mahe
 * @since 25/09/2024
 */
data class GridOffsets(
    val topStartCorner: Offset,
    val bottomStartCorner: Offset,
    val topEndCorner: Offset,
    val bottomEndCorner: Offset,
)
