package com.example.cmpexploreexplainpreserve.navigation3

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.cmpexploreexplainpreserve.HomeScreen
import com.example.cmpexploreexplainpreserve.dataStorePref.DataStoreScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass


@Composable
fun AppNavigation(
    prefs: DataStore<Preferences>
) {

    val mySerializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeRoute::class)
            subclass(DataStoreRoute::class)
        }
    }

    val configuration = SavedStateConfiguration {
        serializersModule = mySerializersModule
    }

    val backStack = rememberNavBackStack(
        configuration = configuration,
        HomeRoute
    )

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<HomeRoute> {
                HomeScreen(
                    onDataStoreNavigation = {
                        backStack.add(DataStoreRoute)
                    }
                )
            }
            entry<DataStoreRoute> {
                DataStoreScreen(prefs)
            }

        }
    )

}