package com.example.cmpexploreexplainpreserve

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cmpexploreexplainpreserve.notifications.NotificationManager
import org.koin.compose.koinInject

@Composable
fun HomeScreen(
    onDataStoreNavigation: () -> Unit,
    onExampleDatabaseNavigation: () -> Unit
) {
    val notificationManager = koinInject<NotificationManager>()

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

        Button(
            onClick = {
                notificationManager.showNotification(
                    title = "Hello from Koin!",
                    description = "This notification was sent using an injected manager."
                )
            },
            content = { Text("Show notification") }
        )
    }
}