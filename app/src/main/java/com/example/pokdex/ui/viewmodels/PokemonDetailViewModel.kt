package com.example.pokdex.ui.viewmodels

import androidx.compose.runtime.getValue
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
import com.example.pokdex.model.PokemonDetail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PokemonDetailViewModel(
    private val pokemonDetailRepository: PokemonDetailRepository,
    savedStateHandle: SavedStateHandle,

) : ViewModel() {
    private val id: Int = checkNotNull(savedStateHandle["id"])
    lateinit var pokemon: StateFlow<PokemonDetail>

    init {
        getPokemon()
    }

    private fun getPokemon() {
        pokemon = pokemonDetailRepository.getPokemonDetail(id)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = PokemonDetail(),
            )
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication
                val pokemonDetailRepository = application.container.pokemonDetailRepository
                PokemonDetailViewModel(pokemonDetailRepository, this.createSavedStateHandle())
            }
        }
    }
}
