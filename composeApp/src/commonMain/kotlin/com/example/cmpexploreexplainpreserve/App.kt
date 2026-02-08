package com.example.cmpexploreexplainpreserve

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import cmp_exploreexplainpreserve.composeapp.generated.resources.Res
import cmp_exploreexplainpreserve.composeapp.generated.resources.compose_multiplatform
import com.example.cmpexploreexplainpreserve.dataStore.PreferenceKeys
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun App(
    prefs: DataStore<Preferences>
) {

    val scope = rememberCoroutineScope()
    var randomNumber: Int
    val randomNumberPref by prefs
        .data
        .map {
            val counterKey = intPreferencesKey(PreferenceKeys.RANDOM_NUMBER.name)
            it[counterKey] ?: 0
        }
        .collectAsState(0)

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = {
                showContent = !showContent
                randomNumber = (0..100).random()

                println("RandomNumber: $randomNumber")

                scope.launch {
                    prefs.edit { preferences ->
                        preferences[PreferenceKeys.RANDOM_NUMBER] = randomNumber + 1
                    }
                }
            }
            ) {
                Text("Click me or not!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")

                }
            }
            Text("Random number: $randomNumberPref")
        }
    }
}

