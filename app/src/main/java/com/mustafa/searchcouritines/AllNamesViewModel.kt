package com.mustafa.searchcouritines

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class AllNamesViewModel : ViewModel() {

    private val _mutableNamesList = MutableSharedFlow<List<String>>()
    val namesList: SharedFlow<List<String>> = _mutableNamesList
    private val names = listOf(
        "Muhammad", "Ahmed", "Ali", "Khalid", "Omar",
        "Fatima", "Layla", "Zainab", "Sara", "Maryam",
        "Yusuf", "Hassan", "Hussein", "Abdullah", "Mustafa",
        "Nour", "Rana", "Hana", "Sama", "Amina",
        "Tariq", "Bassam", "Nasser", "Rami", "Wissam",
        "Lama", "Jana", "Dalia", "Ghada", "Iman", "Ziad"
    )


    init {
        viewModelScope.launch {
            _mutableNamesList
                .emit(names)
        }
    }

    fun filterList(query: String) {
        viewModelScope.launch {
            _mutableNamesList
                .emit(
                    names.filter {
                        it.startsWith(query, true)
                    }
                )
        }
    }
}