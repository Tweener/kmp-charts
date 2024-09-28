package com.tweener.charts.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
@Immutable
open class ChartSizes internal constructor(
    private val axisStrokeWidth: Dp,
    private val axisDashOn: Dp,
    private val axisDashOff: Dp,
    private val axisXValuesPadding: Dp,
    private val axisYValuesPadding: Dp,
) {
    internal fun axisStrokeWidth(): Dp = axisStrokeWidth

    internal fun axisDashOn(): Dp = axisDashOn

    internal fun axisDashOff(): Dp = axisDashOff

    internal fun axisXValuesPadding(): Dp = axisXValuesPadding

    internal fun axisYValuesPadding(): Dp = axisYValuesPadding
}
