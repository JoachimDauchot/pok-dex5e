package com.example.pokdex.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokdex.data.network.PokemonSummary.pokemonSummaryService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    init {
        getSummaries()
    }

    fun changeName(name: String) {
        _name.update {
            name
        }
    }
    private fun getSummaries() {
        viewModelScope.launch {
            val result = pokemonSummaryService.getSummaries()
            println(result)
        }
    }
}
