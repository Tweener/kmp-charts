package com.tweener.charts.model

import com.tweener.charts.type.line.model.LineType
import com.tweener.charts.type.line.model.LineType.Companion.DefaultType

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
data class XAxis<X>(
    val values: List<XAxisValue<X>>,
    val lineType: LineType = DefaultType,
)
