package com.example.pokdex.ui.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokdex.PokedexApplication
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import kotlinx.coroutines.launch

class SummaryViewModel(
    private val pokemonSummaryRepository: PokemonSummaryRepository,
) : ViewModel() {
    init {
        getSummaries()
    }

    private fun getSummaries() {
        viewModelScope.launch {
            val result = pokemonSummaryRepository.getSummaries()
            println(result)
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as PokedexApplication
                val pokemonSummaryRepository = application.container.pokemonSummaryRepository
                SummaryViewModel(pokemonSummaryRepository)
            }
        }
    }
}
