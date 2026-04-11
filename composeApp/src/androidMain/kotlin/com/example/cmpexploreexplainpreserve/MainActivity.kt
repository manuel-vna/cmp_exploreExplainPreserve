package com.example.cmpexploreexplainpreserve

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.cmpexploreexplainpreserve.dataStorePref.createDataStorePreferences
import com.example.cmpexploreexplainpreserve.documentScanner.AndroidDocumentScanner
import com.example.cmpexploreexplainpreserve.navigation3.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val documentScanner = AndroidDocumentScanner(this)

        setContent {
            MaterialTheme {
                AppNavigation(
                    prefs = remember {
                        createDataStorePreferences(applicationContext)
                    },
                    onScan = {
                        documentScanner.scan()
                    }
                )

                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        when (event) {
                            Lifecycle.Event.ON_START -> println("Activity: onStart")
                            Lifecycle.Event.ON_RESUME -> println("Activity: onResume")
                            Lifecycle.Event.ON_DESTROY -> println("Activity: onDestroy")
                            Lifecycle.Event.ON_PAUSE -> println("Activity: onPause")
                            else -> {
                                println("Activity: $event")
                            }
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

            }
        }
    }
}
