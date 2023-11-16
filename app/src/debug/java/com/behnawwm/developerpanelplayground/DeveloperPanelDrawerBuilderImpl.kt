package com.behnawwm.developerpanelplayground

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable

class DeveloperPanelDrawerBuilderImpl : DeveloperPanelDrawerBuilder {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun ContainerContent(content: @Composable () -> Unit) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    DeveloperPanelContent()
                }
            },
            content = content
        )
    }
}