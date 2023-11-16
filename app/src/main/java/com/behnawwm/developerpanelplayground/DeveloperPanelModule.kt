package com.behnawwm.developerpanelplayground

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DeveloperPanelModule {

    @Provides
    fun provideNotificationBuilder(): DeveloperPanelNotificationBuilder {
        return DeveloperPanelNotificationBuilderImpl()
    }

    @Provides
    fun provideFloatingButtonBuilder(): DeveloperPanelFloatingButtonBuilder {
        return DeveloperPanelFloatingButtonBuilderImpl()
    }
}