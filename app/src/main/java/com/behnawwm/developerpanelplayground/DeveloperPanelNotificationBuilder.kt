package com.behnawwm.developerpanelplayground

import android.content.Context
import androidx.activity.ComponentActivity

interface DeveloperPanelNotificationBuilder {
    fun createNotification(activity: ComponentActivity)
}