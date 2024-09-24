package com.tweener.charts.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
@Immutable
open class ChartColors internal constructor(
    private val xAxisValues: Color,
    private val yAxisValues: Color,
) {
    internal fun xAxisValues(): Color = xAxisValues

    internal fun yAxisValues(): Color = yAxisValues
}
