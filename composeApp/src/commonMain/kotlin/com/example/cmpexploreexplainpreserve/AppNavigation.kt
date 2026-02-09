package com.example.cmpexploreexplainpreserve

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.cmpexploreexplainpreserve.dataStorePref.DataStoreScreen
import com.example.cmpexploreexplainpreserve.navigation3.DataStoreRoute
import com.example.cmpexploreexplainpreserve.navigation3.HomeRoute
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic


@Composable
fun AppNavigation() {

    // Provide a SavedStateConfiguration with serializers for polymorphic keys
    val config = remember {
        SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(HomeRoute::class, HomeRoute.serializer())
                    subclass(DataStoreRoute::class, DataStoreRoute.serializer())
                }
            }
        }
    }

    // Back stack: initial screen is HomeRoute
    val backStack = rememberNavBackStack(config, HomeRoute)

    // Display navigation UI
    NavDisplay(backStack = backStack) { entry ->
        when (val route = entry.key) {
            is HomeRoute -> HomeScreen(onNavigate = { id ->
                backStack.add(DataStoreRoute(id))  // push new screen
            })

            is DataStoreRoute -> DataStoreScreen(route.itemId, onBack = {
                backStack.remove(route)
            })
        }
    }
}