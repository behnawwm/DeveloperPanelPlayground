package com.behnawwm.developerpanelplayground

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Module
@InstallIn(SingletonComponent::class)
object DeveloperPanelModule {

    @Provides
    fun provideNotificationBuilder(): DeveloperPanelNotificationBuilder {
        return object : DeveloperPanelNotificationBuilder {
            override fun createNotification(activity: ComponentActivity) {}
        }
    }

    @Provides
    fun provideFloatingButtonBuilder(): DeveloperPanelFloatingButtonBuilder {
        return object : DeveloperPanelFloatingButtonBuilder {
            @Composable
            override fun Content() {
            }
        }
    }

    @Provides
    fun provideDrawerBuilder(): DeveloperPanelDrawerBuilder {
        return object : DeveloperPanelDrawerBuilder {
            @Composable
            override fun ContainerContent(content: @Composable () -> Unit) {
                Box(modifier = Modifier.fillMaxSize()) {
                    content()
                }
            }
        }
    }
}