package com.example.pokdex.ui.viewmodels

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokdex.PokedexApplication
import com.example.pokdex.data.repositories.PokemonDetailRepository
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.example.pokdex.model.PokemonDetail
import com.example.pokdex.model.PokemonSummary
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val pokemonDetailRepository: PokemonDetailRepository,
    private val pokemonSummaryRepository: PokemonSummaryRepository,
    savedStateHandle: SavedStateHandle,

) : ViewModel() {
    private val id: Int = checkNotNull(savedStateHandle["id"])
    var isLoading: Boolean by mutableStateOf(true)
    var pokemon: PokemonDetail by mutableStateOf(PokemonDetail())
    var summaries: MutableMap<String,PokemonSummary> by mutableStateOf(mutableMapOf())

    init {
        viewModelScope.launch {
            isLoading = true
            getPokemon()
            getSummaries()
            isLoading = false
        }
    }

    private suspend fun getPokemon() {
        pokemon = pokemonDetailRepository.getPokemonDetail(id).first()
    }

    fun getSummaryImage(filename: String): Bitmap {
        return pokemonSummaryRepository.retrieveImage(filename)
    }

    private suspend fun getSummaries() {
        try {
            val summaryInto = pokemonSummaryRepository.getSummary(pokemon.evolve!!.into[0])
            summaries.put("Into", summaryInto)
        } catch (e: Exception) {
            Log.i("Evolution Into", "This pokemon does not evolve")
        }
        try {
            val summaryFrom = pokemonSummaryRepository.getSummary(pokemon.evolve!!.from[0])
            summaries.put("From", summaryFrom)
        } catch (e: Exception) {
            Log.i("Evolution From", "This pokemon has no prevolve")
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication
                val pokemonDetailRepository = application.container.pokemonDetailRepository
                val pokemonSummaryRepository = application.container.pokemonSummaryRepository
                PokemonDetailViewModel(pokemonDetailRepository, pokemonSummaryRepository, this.createSavedStateHandle())
            }
        }
    }
}
