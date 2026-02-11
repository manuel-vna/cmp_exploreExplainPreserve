package com.example.cmpexploreexplainpreserve

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onDataStoreNavigation: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center),
        onClick = {
            onDataStoreNavigation()
        },
        content = { Text("Data Store Preferences") }
    )
}