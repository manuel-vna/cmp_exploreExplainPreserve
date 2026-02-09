package com.example.cmpexploreexplainpreserve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.example.cmpexploreexplainpreserve.dataStorePref.createDataStorePreferences

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                prefs = remember {
                    createDataStorePreferences(applicationContext)
                }
            )
        }
    }
}
