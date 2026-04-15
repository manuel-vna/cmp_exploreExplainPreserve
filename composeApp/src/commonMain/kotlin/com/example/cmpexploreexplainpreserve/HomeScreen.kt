package com.example.cmpexploreexplainpreserve

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onDataStoreNavigation: () -> Unit,
    onExampleDatabaseNavigation: () -> Unit,
    onScan: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                onScan()
            },
            content = { Text("Document Scanner") }
        )
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