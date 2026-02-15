package com.example.cmpexploreexplainpreserve.dataStorePref

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DataStoreScreen(
    prefs: DataStore<Preferences>
) {
    // Wrap the ViewModel creation in remember so it's not recreated on every recomposition
    val viewModel = remember(prefs) { DataStorePrefViewModel(prefs) }
    val debounceMillis: Long = 1000L

    val favouriteNumberPref by viewModel.favouriteNumberPref.collectAsStateWithLifecycle()
    val favouriteNumber by viewModel.favouriteNumber.collectAsStateWithLifecycle()

    LaunchedEffect(favouriteNumber) {
        if (favouriteNumber?.isBlank() == true) return@LaunchedEffect

        // Wait for the user to stop typing
        kotlinx.coroutines.delay(debounceMillis)

        // If `text` hasn't changed during the delay, call the callback
        viewModel.onDebouncedInput() //favouriteNumber)
    }

    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "DataStore Preferences"
        )

        TextField(
            value = favouriteNumber ?: "",
            onValueChange = {
                viewModel.editFavouriteNumber(favouriteNumber = it)
            }
        )

        Text(
            text = "Current Value: $favouriteNumberPref"
        )

    }

}
