package com.practical

import android.app.Application
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


class ApplicationClass: Application() {

    val API_URL = "https://mobileapp.annabelleme.com/"

    override fun onCreate() {
        super.onCreate()
        plant(DebugTree())
    }
}