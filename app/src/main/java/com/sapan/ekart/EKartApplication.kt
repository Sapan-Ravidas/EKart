package com.sapan.ekart

import android.app.Application
import com.sapan.ekart.analytics.AnalyticsWorkManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EKartApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AnalyticsWorkManager.scheduleAnalyticsUpload(this)
    }
}

