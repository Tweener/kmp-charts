package com.tweener.charts._internal.kotlinextension

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import com.tweener.charts.computeValueHeight
import com.tweener.charts.computeValueWidth
import com.tweener.charts.model.Axis

/**
 * @author Vivien Mahe
 * @since 25/09/2024
 */

/**
 * Computes the maximum width from the list of values.
 */
fun <T> Axis<T>.computeValueMaxWidth(textMeasurer: TextMeasurer, textStyle: TextStyle): Float {
    var maxValueWidth = 0f
    values.forEach { value ->
        val valueWidth = value.computeValueWidth(textMeasurer = textMeasurer, textStyle = textStyle)
        if (valueWidth > maxValueWidth) maxValueWidth = valueWidth
    }

    return maxValueWidth
}

/**
 * Computes the maximum height from the list of values.
 */
fun <T> Axis<T>.computeValueMaxHeight(textMeasurer: TextMeasurer, textStyle: TextStyle): Float {
    var maxValueHeight = 0f
    values.forEach { value ->
        val valueHeight = value.computeValueHeight(textMeasurer = textMeasurer, textStyle = textStyle)
        if (valueHeight > maxValueHeight) maxValueHeight = valueHeight
    }

    return maxValueHeight
}
