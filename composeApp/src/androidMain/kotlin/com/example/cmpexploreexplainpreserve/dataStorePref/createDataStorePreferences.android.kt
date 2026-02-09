package com.example.cmpexploreexplainpreserve.dataStorePref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStorePreferences(context: Context): DataStore<Preferences> {
    return createDataStorePreferences {
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }
}