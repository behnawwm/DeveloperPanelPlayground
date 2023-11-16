package com.behnawwm.developerpanelplayground

import androidx.compose.runtime.Composable

interface DeveloperPanelDrawerBuilder {
    @Composable
    fun ContainerContent(content: @Composable () -> Unit)
}