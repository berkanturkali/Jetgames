package com.example.jetgames

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class JetgamesApp : Application() {

    @Inject
    lateinit var timber: Timber.Tree

    override fun onCreate() {
        super.onCreate()
        Timber.plant(timber)
    }
}
