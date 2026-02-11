package com.example.cmpexploreexplainpreserve

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.example.cmpexploreexplainpreserve.dataStorePref.createDataStorePreferences

@Suppress("Unused", "This method is called by the iOS system")
fun MainViewController() = ComposeUIViewController {
    AppNavigation(
        prefs = remember {
            createDataStorePreferences()
        }
    )

}