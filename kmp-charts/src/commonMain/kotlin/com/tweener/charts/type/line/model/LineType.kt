package com.tweener.charts.type.line.model

import androidx.compose.runtime.Immutable
import kotlin.jvm.JvmInline

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
@Immutable
@JvmInline
value class LineType(private val value: Int) {

    companion object {
        val Straight = LineType(0)

        val Curved = LineType(1)

        /**
         * Default type used when drawing the line.
         */
        val DefaultType = Straight
    }

    override fun toString() = when (this) {
        Straight -> "Straight"
        Curved -> "Curved"
        else -> "Unknown"
    }
}
