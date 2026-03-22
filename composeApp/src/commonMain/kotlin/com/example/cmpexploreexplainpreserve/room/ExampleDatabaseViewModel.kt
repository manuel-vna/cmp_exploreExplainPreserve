package com.example.cmpexploreexplainpreserve.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExampleDatabaseViewModel(
    private val exampleDao: ExampleDao
) : ViewModel() {

    private val _userName = MutableStateFlow<String>("")
    val userName = _userName.asStateFlow()


    val savedNames: StateFlow<List<ExampleData>> = exampleDao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateUserName(newName: String) {
        _userName.value = newName
    }

    fun saveUser(nameToSave: String) {
        viewModelScope.launch {
            if (_userName.value.isNotBlank()) {
                exampleDao.insert(ExampleData(name = nameToSave))
            }
        }
    }
}