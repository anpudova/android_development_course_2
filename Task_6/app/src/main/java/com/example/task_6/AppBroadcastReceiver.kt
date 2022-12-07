package com.example.task_6

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class AppBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendIntentFlag: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendIntent = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), pendIntentFlag)

        NotificationHandler(context).createNotification(
            intent?.extras?.getString(HEAD_TEXT),
            intent?.extras?.getString(SHORT_TEXT),
            intent?.extras?.getString(EXTENDED_TEXT),
            pendIntent
        )
    }

    companion object {
        const val HEAD_TEXT = "HEAD_TEXT"
        const val SHORT_TEXT = "SHORT_TEXT"
        const val EXTENDED_TEXT = "EXTENDED_TEXT"
    }

}