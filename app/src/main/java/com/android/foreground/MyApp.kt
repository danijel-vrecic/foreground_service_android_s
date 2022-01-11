package com.android.foreground

import android.app.Application
import android.app.ForegroundServiceStartNotAllowedException

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        try {
            startForegroundService(DummyForegroundService.getCallingIntent(this))
        } catch (e: ForegroundServiceStartNotAllowedException) {
            e.printStackTrace()
        }
    }
}