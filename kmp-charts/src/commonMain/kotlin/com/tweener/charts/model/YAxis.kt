package com.tweener.charts.model

import com.tweener.charts.type.line.model.LineType
import com.tweener.charts.type.line.model.LineType.Companion.DefaultType

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
data class YAxis<Y>(
    val values: List<YAxisValue<Y>>,
    val lineType: LineType = DefaultType,
)
