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
import androidx.compose.material3.Text
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.behnawwm.developerpanelplayground.ui.theme.DeveloperPanelPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotification()
        setContent {
            DeveloperPanelPlaygroundTheme {
                Text(text = "main activity")
            }
        }
    }

    private fun createNotification() {
        checkOrRequestNotificationPermission()
        createNotificationChannel()

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_developer_panel_foreground)
            .setContentTitle("DevPan")
            .setContentText("Click here to view the DevPan")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(false)
            .setContentIntent(
                createLaunchDeveloperPanelActivityPendingIntent()
            )

        with(NotificationManagerCompat.from(this)) {
            val notificationId = 1
            notify(notificationId, builder.build())
        }
    }

    private fun createLaunchDeveloperPanelActivityPendingIntent(): PendingIntent {
        val intent = Intent(this, DeveloperPanelActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun checkOrRequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    baseContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    Toast.makeText(baseContext, "isGranted: $isGranted", Toast.LENGTH_SHORT).show()
                }
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {
                    description = CHANNEL_DESCRIPTION
                }
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    companion object {
        const val CHANNEL_ID = "DEVELOPER_PANEL_CHANNEL_ID"
        const val CHANNEL_NAME = "DEVELOPER_PANEL_CHANNEL_NAME"
        const val CHANNEL_DESCRIPTION = "DEVELOPER_PANEL_CHANNEL_DESCRIPTION"
    }
}