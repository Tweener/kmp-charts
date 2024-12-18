package com.tweener.charts.type.donut.model

import androidx.annotation.FloatRange
import androidx.compose.ui.graphics.Color
import com.tweener.kmpkit.utils.Degrees

/**
 * @author Vivien Mahe
 * @since 05/03/2024
 */

data class Segment(
    val id: String,
    val angle: Degrees,
    @FloatRange(from = 0.0, to = 1.0) val progress: Float = 1f,
    val baseColor: Color,
    val backgroundColor: Color = baseColor.copy(alpha = 0.1f),
    val enabled: Boolean = true,
    val selected: Boolean = false,
)
