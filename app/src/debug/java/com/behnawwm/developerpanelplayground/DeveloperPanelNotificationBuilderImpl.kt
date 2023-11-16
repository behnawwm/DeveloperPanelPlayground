package com.behnawwm.developerpanelplayground

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class DeveloperPanelNotificationBuilderImpl : DeveloperPanelNotificationBuilder {

    override fun createNotification(activity: ComponentActivity) {
        checkOrRequestNotificationPermission(activity)
        createNotificationChannel(activity)

        val builder = NotificationCompat.Builder(activity, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_developer_panel_foreground)
            .setContentTitle("DevPan")
            .setContentText("Click here to view the DevPan")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(false)
            .setContentIntent(
                createDeveloperPanelActivityPendingIntent(activity)
            )

        with(NotificationManagerCompat.from(activity)) {
            val notificationId = 1
            notify(notificationId, builder.build())
        }
    }

    private fun createDeveloperPanelActivityPendingIntent(context: Context): PendingIntent =
        PendingIntent.getActivity(
            context,
            0,
            createDeveloperPanelActivityIntent(context),
            PendingIntent.FLAG_IMMUTABLE
        )

    private fun createDeveloperPanelActivityIntent(context: Context): Intent {
        return Intent(context, DeveloperPanelActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private fun checkOrRequestNotificationPermission(activity: ComponentActivity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            val requestPermissionLauncher =
                activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    Toast.makeText(activity, "isGranted: $isGranted", Toast.LENGTH_SHORT).show()
                }
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
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