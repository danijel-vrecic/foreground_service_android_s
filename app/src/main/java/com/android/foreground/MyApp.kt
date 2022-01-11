package com.android.foreground

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startForegroundService(DummyForegroundService.getCallingIntent(this))
    }
}