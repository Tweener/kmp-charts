package com.tweener.charts.model

import androidx.compose.runtime.Immutable
import kotlin.jvm.JvmInline

/**
 * @author Vivien Mahe
 * @since 24/09/2024
 */
@Immutable
@JvmInline
value class StrokeStyle(private val value: Int) {

    companion object {
        val Solid = StrokeStyle(0)

        val Dashed = StrokeStyle(1)

        val Dotted = StrokeStyle(2)

        /**
         * Default style used for the stroke.
         */
        val DefaultStyle = Solid
    }

    override fun toString() = when (this) {
        Solid -> "Solid"
        Dashed -> "Dashed"
        Dotted -> "Dotted"
        else -> "Unknown"
    }
}
