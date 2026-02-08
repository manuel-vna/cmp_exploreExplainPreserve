package com.example.cmpexploreexplainpreserve

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.example.cmpexploreexplainpreserve.dataStore.createDataStorePreferences

fun MainViewController() = ComposeUIViewController {
    App(
        prefs = remember {
            createDataStorePreferences()
        }
    )

}