package com.example.task8

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHandler(private val context: Context?) {

    var notificationManager: NotificationManager? = null
    var notificationBuilder: NotificationCompat.Builder? = null

    fun createNotification(headText: String?, shortMsgText: String?, intent: PendingIntent): Notification {
        createNotificationChannel()
        notificationBuilder = NotificationCompat.Builder(context!!, DEFAULT_CHANNEL_ID)
            .setContentTitle(headText)
            .setContentText(shortMsgText)
            .setSmallIcon(R.drawable.icon_notification)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentIntent(intent)
        notificationManager?.notify(NOTIFICATION_ID, notificationBuilder!!.build())
        return notificationBuilder!!.build()
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

    fun updateTextNotification(shortMsgText: String?) {
        notificationBuilder?.setContentText(shortMsgText)
        notificationManager?.notify(NOTIFICATION_ID, notificationBuilder?.build())
    }

    companion object {
        const val NOTIFICATION_ID = 111
        private const val DEFAULT_CHANNEL_ID = "DEFAULT_CHANNEL"
    }
}