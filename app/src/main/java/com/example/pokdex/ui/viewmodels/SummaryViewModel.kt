package com.example.pokdex.ui.viewmodels

import android.util.Log
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
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException

class SummaryViewModel(
    private val pokemonSummaryRepository: PokemonSummaryRepository,
) : ViewModel() {
    lateinit var summaries: StateFlow<List<PokemonSummary>>
    init {
        getSummaries()
    }
    val baseImgUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/"
    private fun getSummaries() {
        viewModelScope.launch {
            try {
                pokemonSummaryRepository.refresh()
            } catch (e: SocketTimeoutException) {
                Log.i("API", "API is down")
            }
        }
        summaries = pokemonSummaryRepository.getSummaries()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = listOf(PokemonSummary()),
            )
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
