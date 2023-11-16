package com.behnawwm.developerpanelplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeveloperPanelContent(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Developer Panel Content",
            color = Color.White,
            modifier = modifier,
        )
        Text(
            text = "(tools, mocks, feature flags, etc.)",
            color = Color.White,
            modifier = modifier,
        )
    }
}