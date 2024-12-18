package com.tweener.charts.type.donut

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.tweener.charts.type.donut.model.Segment
import com.tweener.kmpkit.kotlinextensions.degrees
import com.tweener.czan.preview.CzanThemePreview
import com.tweener.czan.preview.UiModePreviews
import io.github.aakira.napier.Napier

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

    CzanThemePreview {
        DonutChart(
            segments = listOf(
                Segment(id = "#1", angle = 40f.degrees, progress = 0.33f, baseColor = green),
                Segment(id = "#2", angle = 20f.degrees, progress = 0.7f, baseColor = red, backgroundColor = darkRed),
                Segment(id = "#3", angle = 90f.degrees, progress = 0.66f, baseColor = green),
                Segment(id = "#4", angle = 60f.degrees, progress = 0.7f, baseColor = red, backgroundColor = darkRed),
                Segment(id = "#5", angle = 50f.degrees, progress = 0.8f, baseColor = orange),
                Segment(id = "#6", angle = 100f.degrees, progress = 1f, baseColor = gray, enabled = false),
            ),
            startAngleFromOrigin = 270f.degrees,
            sizes = DonutChartDefault.chartSizes(strokeWidth = 12.dp, selectedStrokeWidth = 22.dp),
            animationDurationMillis = 800,
            strokeCap = StrokeCap.Round,
            onSegmentClicked = { segment, active ->
                Napier.d { "Segment clicked: ${segment.id}, isActive? $active" }
            }
        )
    }
}
