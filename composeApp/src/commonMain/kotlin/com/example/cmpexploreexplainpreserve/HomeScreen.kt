package com.example.cmpexploreexplainpreserve

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onDataStoreNavigation: () -> Unit,
    onExampleDatabaseNavigation: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                onDataStoreNavigation()
            },
            content = { Text("Data Store Preferences") }
        )

        Button(
            onClick = {
                onExampleDatabaseNavigation()
            },
            content = { Text("Room Database") }
        )
    }
}