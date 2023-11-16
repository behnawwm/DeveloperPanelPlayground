package com.behnawwm.developerpanelplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DeveloperPanelContent(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Developer Panel Content (tools, mocks, feature toggles, etc.)",
            color = Color.White,
            modifier = modifier,
        )
    }
}