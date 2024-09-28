package com.tweener.charts.model

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tweener.czan.theme.Size

/**
 * @author Vivien Mahe
 * @since 25/09/2024
 */

object ChartDefaults {

    object GridVisibility {
        val ShowXAxis = true
        val ShowYAxis = true
        val ShowXGrid = true
        val ShowYGrid = true
    }

    object ChartSizes {
        val AxisStrokeWidth = 4.dp
        val AxisDashOn = 10.dp
        val AxisDashOff = 10.dp
        val AxisXValuesPadding = Size.Padding.ExtraSmall
        val AxisYValuesPadding = Size.Padding.ExtraSmall
    }

    object ChartColors {
        val XAxisValues: Color
            @Composable get() = MaterialTheme.colorScheme.onBackground

        val XAxisGrid: Color
            @Composable get() = MaterialTheme.colorScheme.outline

        val YAxisValues: Color
            @Composable get() = MaterialTheme.colorScheme.onBackground

        val YAxisGrid: Color
            @Composable get() = MaterialTheme.colorScheme.outline
    }
}
