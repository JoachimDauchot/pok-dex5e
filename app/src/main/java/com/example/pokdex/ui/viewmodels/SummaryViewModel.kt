package com.example.pokdex.ui.viewmodels

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokdex.PokedexApplication
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.example.pokdex.model.PokemonSummary
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class SummaryViewModel(
    private val pokemonSummaryRepository: PokemonSummaryRepository,
) : ViewModel() {
    lateinit var summaries: StateFlow<List<PokemonSummary>>

    init {
        getSummaries()
    }

    var filterText: String by mutableStateOf("")

    private fun getSummaries() {
        summaries = pokemonSummaryRepository.getSummaries()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = listOf(PokemonSummary()),
            )
    }
    suspend fun getImage(filename: String): Bitmap {
        return pokemonSummaryRepository.loadSummaryImage(filename)
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
