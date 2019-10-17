package com.example.android.dessertpusher

import android.app.Application
import timber.log.Timber

/**
 * PusherApplication is an application class for the whole application, which contains things related to all of the application
 * like Timber
 */
class PusherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}