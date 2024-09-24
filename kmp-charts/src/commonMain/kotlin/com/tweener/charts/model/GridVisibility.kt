package com.tweener.charts.model

import androidx.compose.runtime.Immutable

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
@Immutable
open class GridVisibility internal constructor(
    private val showXAxis: Boolean,
    private val showYAxis: Boolean,
    private val showXGrid: Boolean,
    private val showYGrid: Boolean,
) {
    internal fun showXAxis(): Boolean = showXAxis

    internal fun showYAxis(): Boolean = showYAxis

    internal fun showXGrid(): Boolean = showXGrid

    internal fun showYGrid(): Boolean = showYGrid
}
