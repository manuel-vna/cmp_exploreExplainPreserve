package com.example.cmpexploreexplainpreserve

import android.app.Application
import com.example.cmpexploreexplainpreserve.koin.initKoin
import com.example.cmpexploreexplainpreserve.notifications.NotificationManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainApplication : Application(), KoinComponent {

    private val notificationManager: NotificationManager by inject()

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
        notificationManager.createNotificationChannel()
    }
}