package com.example.cmpexploreexplainpreserve.koin

import android.preference.PreferenceManager
import com.example.cmpexploreexplainpreserve.notifications.NotificationManager
import com.example.cmpexploreexplainpreserve.room.ExampleDatabase
import com.example.cmpexploreexplainpreserve.room.getExampleDatabase
import com.example.cmpexploreexplainpreserve.room.getRoomDatabase
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<ExampleDatabase> {
        val builder = getExampleDatabase(context = androidContext())
        getRoomDatabase(builder)
    }
    factory<Settings> {
        SharedPreferencesSettings(
            PreferenceManager.getDefaultSharedPreferences(androidContext())
        )
    }
    single { NotificationManager(context = androidContext()) }
}