package com.example.cmpexploreexplainpreserve

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.cmpexploreexplainpreserve.dataStorePref.DATA_STORE_FILE_NAME
import com.example.cmpexploreexplainpreserve.dataStorePref.createDataStorePreferences

fun main() {

    val prefs = createDataStorePreferences {
        DATA_STORE_FILE_NAME
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "cmp_exploreExplainPreserve",
        ) {
            AppNavigation(
                prefs = prefs
            )
        }
    }

}