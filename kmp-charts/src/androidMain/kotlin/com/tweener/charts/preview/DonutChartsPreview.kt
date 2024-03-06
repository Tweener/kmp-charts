package com.tweener.charts.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tweener.charts.DonutChart
import com.tweener.charts.DonutChartDefault
import com.tweener.charts.Segment
import com.tweener.common._internal.kotlinextensions.degrees
import com.tweener.czan.preview.UiModePreviews

/**
 * @author Vivien Mahe
 * @since 05/03/2024
 */

@UiModePreviews
@Composable
private fun DonutChartPreview() {
    val green = Color(0xFF04C700)
    val orange = Color(0xFFFF8850)
    val red = Color(0xFFFF3434)
    val darkRed = Color(0xFFA40000)
    val gray = Color(0xFFECECEC)

    DonutChart(
        segments = listOf(
            Segment(angle = 40f.degrees, progress = 0.33f, baseColor = green),
            Segment(angle = 20f.degrees, progress = 0.7f, baseColor = red, backgroundColor = darkRed),
            Segment(angle = 90f.degrees, progress = 0.66f, baseColor = green),
            Segment(angle = 60f.degrees, progress = 0.7f, baseColor = red, backgroundColor = darkRed),
            Segment(angle = 50f.degrees, progress = 0.8f, baseColor = orange),
            Segment(angle = 100f.degrees, progress = 1f, baseColor = gray),
        ),
        startAngleFromOrigin = 270f.degrees,
        sizes = DonutChartDefault.chartSizes(strokeWidth = 12.dp, selectedStrokeWidth = 22.dp),
        animationDurationMillis = 800,
    )
}
