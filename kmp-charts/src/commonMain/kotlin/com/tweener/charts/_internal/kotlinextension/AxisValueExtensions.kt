package com.tweener.charts._internal.kotlinextension

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import com.tweener.charts.model.AxisValue

/**
 * @author Vivien Mahe
 * @since 25/09/2024
 */

/**
 * Computes the axis value width.
 */
fun <T> AxisValue<T>.computeValueWidth(textMeasurer: TextMeasurer, textStyle: TextStyle): Float =
    textMeasurer.measure(text = AnnotatedString(name), style = textStyle).size.width.toFloat()

/**
 * Computes the axis value height.
 */
fun <T> AxisValue<T>.computeValueHeight(textMeasurer: TextMeasurer, textStyle: TextStyle): Float =
    textMeasurer.measure(text = AnnotatedString(name), style = textStyle).size.height.toFloat()
