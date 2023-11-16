package com.behnawwm.developerpanelplayground

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DeveloperPanelModule {

    @Provides
    fun provideAnalyticsService(): DeveloperPanelNotificationBuilder {
        return DeveloperPanelNotificationBuilderImpl()
    }
}