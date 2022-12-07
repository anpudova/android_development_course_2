package com.example.task8

import android.Manifest
import android.annotation.SuppressLint
import android.app.*
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.IBinder
import android.os.Parcel
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat

class LocationService: Service() {

    private var headText: String = "com.example.task8"
    private var shortMsgText: String? = "gps"
    private var notificationHandler: NotificationHandler? = null
    private var locationManager: LocationManager? = null

    override fun onCreate() {
        super.onCreate()
        notificationHandler = NotificationHandler(applicationContext)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startLocationService()
        return START_STICKY
    }

    @SuppressLint("MissingPermission")
    private fun startLocationService() {
        val pendIntentFlag: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val pendIntent = PendingIntent.getActivity(
                applicationContext,
                0,
                Intent(applicationContext, MainActivity::class.java),
                pendIntentFlag
            )

            startForeground(
                NotificationHandler.NOTIFICATION_ID,
                notificationHandler?.createNotification(headText, shortMsgText, pendIntent)
            )
        }

        locationManager?.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            MIN_TIME_MS,
            MIN_DISTANCE_M
        ) { location ->

            shortMsgText = location.longitude.toString() + " : " + location.latitude.toString()
            notificationHandler?.updateTextNotification(shortMsgText)

        }

    }

    override fun onDestroy() {
        stopForeground(true)
        notificationHandler = null
        locationManager = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        const val MIN_TIME_MS = 5000L
        const val MIN_DISTANCE_M = 0F
    }
}