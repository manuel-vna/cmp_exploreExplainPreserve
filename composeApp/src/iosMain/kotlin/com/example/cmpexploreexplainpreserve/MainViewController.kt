package com.example.cmpexploreexplainpreserve

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.example.cmpexploreexplainpreserve.dataStorePref.createDataStorePreferences
import com.example.cmpexploreexplainpreserve.koin.initKoinIos
import com.example.cmpexploreexplainpreserve.navigation3.AppNavigation

@Suppress("Unused", "This method is called by the iOS system")
fun MainViewController() = ComposeUIViewController {
    initKoinIos()
    AppNavigation(
        prefs = remember {
            createDataStorePreferences()
        },
        onScan = { println("Not supported on this platform yet") }
    )
}