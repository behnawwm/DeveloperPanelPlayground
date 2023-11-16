package com.behnawwm.developerpanelplayground

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.behnawwm.developerpanelplayground.ui.theme.DeveloperPanelPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var developerPanelNotificationBuilder: DeveloperPanelNotificationBuilder

    @Inject
    lateinit var developerPanelFloatingButtonBuilder: DeveloperPanelFloatingButtonBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        developerPanelNotificationBuilder.createNotification(activity = this@MainActivity)
        setContent {
            DeveloperPanelPlaygroundTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Main Activity",
                        modifier = Modifier.align(Alignment.Center)
                    )
                    developerPanelFloatingButtonBuilder.Content()
                }
            }
        }
    }

}