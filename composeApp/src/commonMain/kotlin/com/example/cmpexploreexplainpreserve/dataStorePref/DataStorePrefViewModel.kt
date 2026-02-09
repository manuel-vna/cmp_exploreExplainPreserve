package com.example.cmpexploreexplainpreserve.dataStorePref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DataStorePrefViewModel(
    val prefs: DataStore<Preferences>
) : ViewModel() {

    private val _randomNumberPref: MutableStateFlow<Int> = MutableStateFlow(value = 0)
    val randomNumberPref: StateFlow<Int> = _randomNumberPref.asStateFlow()

    init {
        // Preferences DataStore exposes the data stored in a Flow<Preferences>
        // that will emit every time a preference has changed.
        viewModelScope.launch {
            prefs.data
                .map {
                    val counterKey = intPreferencesKey(
                        PreferenceKeys.RANDOM_NUMBER.name
                    )
                    it[counterKey] ?: 0
                }
                .collect { value ->
                    _randomNumberPref.value = value
                }
        }
    }

    fun editRandomNumberPref(randomNumber: Int) {
        viewModelScope.launch {
            // To write data, DataStore offers a suspending
            // DataStore.edit(transform: suspend (MutablePreferences) -> Unit) function,
            // which accepts a transform block that allows us to transactionally
            // update the state in DataStore.
            prefs.edit { preferences ->
                preferences[PreferenceKeys.RANDOM_NUMBER] = randomNumber + 1
            }
        }
    }

}