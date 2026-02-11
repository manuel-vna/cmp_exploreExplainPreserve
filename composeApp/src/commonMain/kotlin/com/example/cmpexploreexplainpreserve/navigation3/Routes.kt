package com.example.cmpexploreexplainpreserve.navigation3

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : NavKey
@Serializable
data class DataStoreRoute(val itemId: Int): NavKey