package com.example.cmpexploreexplainpreserve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import com.example.cmpexploreexplainpreserve.dataStorePref.createDataStorePreferences
import com.example.cmpexploreexplainpreserve.navigation3.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                AppNavigation(
                    prefs = remember {
                        createDataStorePreferences(applicationContext)
                    }
                )
            }
        }
    }
}
