package com.example.cmpexploreexplainpreserve.dataStorePref

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_exploreexplainpreserve.composeapp.generated.resources.Res
import cmp_exploreexplainpreserve.composeapp.generated.resources.compose_multiplatform
import cmp_exploreexplainpreserve.composeapp.generated.resources.dataStoreHeadline
import com.example.cmpexploreexplainpreserve.Greeting
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DataStoreScreen(
    prefs: DataStore<Preferences>
) {
    val viewModel = DataStorePrefViewModel(prefs)
    val randomNumberPref by viewModel.randomNumberPref.collectAsStateWithLifecycle()
    var randomNumber: Int

    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = stringResource(resource = Res.string.dataStoreHeadline)
        )

        Button(onClick = {
            showContent = !showContent

            randomNumber = (0..100).random()
            println("Random number: $randomNumber")
            viewModel.editRandomNumberPref(randomNumber)
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
        Text("Random Number retrived from Data StorePreferences:")
        Text("$randomNumberPref")
    }

}