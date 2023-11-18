package com.behnawwm.developerpanelplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.behnawwm.developerpanelplayground.component.DeveloperPanelButton
import com.behnawwm.developerpanelplayground.component.DeveloperPanelNavigateItem
import com.behnawwm.developerpanelplayground.component.DeveloperPanelSection
import com.behnawwm.developerpanelplayground.component.DeveloperPanelToggleable

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
        Spacer(modifier = Modifier.size(8.dp))
        DeveloperPanelSection("Feature Flags") {
            DeveloperPanelToggleable("LoginRedesign", false, {})
            DeveloperPanelToggleable("SplashRefactor", true, {})
            DeveloperPanelToggleable("MainNotifications", false, {})
        }
        Spacer(modifier = Modifier.size(8.dp))
        DeveloperPanelSection("Mocks") {
            DeveloperPanelButton("Log Call", "Log", {})
            DeveloperPanelButton("Socket Response", "Send", {})
        }
        Spacer(modifier = Modifier.size(8.dp))
        DeveloperPanelSection("Logs") {
            DeveloperPanelNavigateItem(
                title = "Socket Calls",
                description = "View socket logs here",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(8.dp))
            DeveloperPanelNavigateItem(
                title = "Crashes",
                description = "View crashes here",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun DeveloperPanelContentPreview() {
    DeveloperPanelContent()
}