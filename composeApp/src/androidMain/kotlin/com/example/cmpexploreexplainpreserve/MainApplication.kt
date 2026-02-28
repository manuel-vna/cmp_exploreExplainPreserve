package com.example.cmpexploreexplainpreserve

import android.app.Application
import com.example.cmpexploreexplainpreserve.koin.initKoin
import org.koin.android.ext.koin.androidContext

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MainApplication)
        }
    }
}