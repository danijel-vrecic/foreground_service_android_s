package com.android.foreground

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.content.getSystemService

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        startForegroundService(DummyForegroundService.getCallingIntent(this))
    }

    private fun createNotificationChannel() {
        val serviceChannel = NotificationChannel(
            DummyForegroundService.CHANNEL_ID,
            "dummy_foreground_service_channel_01",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        getSystemService<NotificationManager>()?.createNotificationChannel(serviceChannel)
    }
}