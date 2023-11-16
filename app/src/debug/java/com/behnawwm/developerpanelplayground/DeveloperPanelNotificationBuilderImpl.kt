package com.behnawwm.developerpanelplayground

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class DeveloperPanelNotificationBuilderImpl : DeveloperPanelNotificationBuilder {

    override fun createNotification(activity: ComponentActivity) {
        if (checkNotificationPermission(activity)) {
            setupNotification(activity)
        } else {
            requestNotificationPermission(
                activity = activity,
                onPermissionGranted = {
                    setupNotification(activity)
                }
            )
        }
    }

    @SuppressLint("InlinedApi")
    private fun requestNotificationPermission(
        activity: ComponentActivity,
        onPermissionGranted: () -> Unit
    ) {
        val requestPermissionLauncher =
            activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted)
                    onPermissionGranted()
            }
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }

    @SuppressLint("MissingPermission")
    private fun setupNotification(activity: ComponentActivity) {
        createNotificationChannel(activity)

        val builder = NotificationCompat.Builder(activity, DEVELOPER_PANEL_CHANNEL_ID)
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

    private fun checkNotificationPermission(activity: ComponentActivity): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        }
        return true
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (notificationManager.getNotificationChannel(DEVELOPER_PANEL_CHANNEL_ID) == null) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(
                    DEVELOPER_PANEL_CHANNEL_ID,
                    DEVELOPER_PANEL_CHANNEL_NAME,
                    importance
                ).apply {
                    description = DEVELOPER_PANEL_CHANNEL_DESCRIPTION
                    setShowBadge(false)
                }
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    companion object {
        private const val DEVELOPER_PANEL_CHANNEL_ID = "DEVELOPER_PANEL_CHANNEL_ID"
        private const val DEVELOPER_PANEL_CHANNEL_NAME = "Developer Panel Channel"
        private const val DEVELOPER_PANEL_CHANNEL_DESCRIPTION =
            "This Channel is for sending the DevPan Notification"
    }
}