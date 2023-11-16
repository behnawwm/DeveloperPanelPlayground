package com.behnawwm.developerpanelplayground

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable

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
}