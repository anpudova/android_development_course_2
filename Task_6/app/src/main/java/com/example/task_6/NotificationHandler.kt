package com.example.task_6

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHandler(private val context: Context?) {

    private var notificationManager: NotificationManager? = null

    fun createNotification(headText: String?, shortMsgText: String?, extendedMsgText: String?, intent: PendingIntent) {
        createNotificationChannel()
        val notificationBuilder = NotificationCompat.Builder(context!!, DEFAULT_CHANNEL_ID)
            .setContentTitle(headText)
            .setContentText(shortMsgText)
            .setSubText(extendedMsgText)
            .setSmallIcon(R.drawable.icon_notif)
            .setContentIntent(intent)
            .setCategory(Notification.CATEGORY_MESSAGE)
            .build()
        notificationManager?.notify(111, notificationBuilder)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (notificationManager?.getNotificationChannel(DEFAULT_CHANNEL_ID) == null) {
                val nameChannel = context?.getString(R.string.name_channel)
                val descrChannel = context?.getString(R.string.description_channel)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(DEFAULT_CHANNEL_ID, nameChannel, importance)
                channel.description = descrChannel
                notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager?.createNotificationChannel(channel)
            }
        }
    }

    companion object {
        private const val DEFAULT_CHANNEL_ID = "DEFAULT_CHANNEL"
    }
}