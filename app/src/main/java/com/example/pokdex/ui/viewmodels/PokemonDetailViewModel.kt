package com.example.pokdex.ui.viewmodels

import android.annotation.SuppressLint
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
import com.example.pokdex.data.repositories.AbilityRepository
import com.example.pokdex.data.repositories.MoveRepository
import com.example.pokdex.data.repositories.PokemonDetailRepository
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.example.pokdex.model.Ability
import com.example.pokdex.model.Move
import com.example.pokdex.model.PokemonDetail
import com.example.pokdex.model.PokemonSummary
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
class PokemonDetailViewModel(
    private val pokemonDetailRepository: PokemonDetailRepository,
    private val pokemonSummaryRepository: PokemonSummaryRepository,
    private val moveRepository: MoveRepository,
    private val abilityRepository: AbilityRepository,
    savedStateHandle: SavedStateHandle,

) : ViewModel() {
    private val id: Int = checkNotNull(savedStateHandle["id"])
    var isLoading: Boolean by mutableStateOf(true)
    var pokemon: PokemonDetail by mutableStateOf(PokemonDetail())
    var summaries: MutableMap<String, ArrayList<PokemonSummary>> by mutableStateOf(mutableMapOf())
    var startingMoves: List<Move> by mutableStateOf(listOf())
    var abilities: List<Ability> by mutableStateOf(listOf())
    var hiddenAbility: Ability by mutableStateOf(Ability())
    var movesPerLevel: Map<Int, List<Move>> by mutableStateOf(mutableMapOf())

    init {
        viewModelScope.launch {
            isLoading = true
            getPokemon()
            getStartingMoves()
            getMovesPerLevel()
            getAbilities()
            getSummaries()
            isLoading = false
        }
    }

    private suspend fun getPokemon() {
        pokemon = pokemonDetailRepository.getPokemonDetail(id).first()
    }

    suspend fun getSummaryImage(filename: String): Bitmap {
        return pokemonSummaryRepository.loadSummaryImage(filename)
    }

    private suspend fun getStartingMoves() {
        startingMoves = pokemon.moves.startingMoves.map { moveName -> moveRepository.getMove(moveName) }
    }

    private suspend fun getMovesPerLevel() {
        movesPerLevel = pokemon.moves.level.mapValues { entry -> entry.value.map { moveName -> moveRepository.getMove(moveName) } }.toSortedMap()
    }

    private suspend fun getSummaries() {
        try {
            val summaryInto = arrayListOf<PokemonSummary>()
            for (pokmn in pokemon.evolve!!.into) {
                summaryInto.add(pokemonSummaryRepository.getSummary(pokmn))
            }
            if (summaryInto.isNotEmpty()) summaries["Into"] = summaryInto
        } catch (e: Exception) {
            Log.i("Evolution Into", "This pokemon does not evolve")
        }
        try {
            val summaryFrom = arrayListOf<PokemonSummary>()
            for (pokmn in pokemon.evolve!!.from) {
                summaryFrom.add(pokemonSummaryRepository.getSummary(pokmn))
            }
            if (summaryFrom.isNotEmpty()) summaries["From"] = summaryFrom
        } catch (e: Exception) {
            Log.i("Evolution From", "This pokemon has no prevolve")
        }
    }
    private suspend fun getAbilities() {
        abilities = pokemon.abilities.map { name -> abilityRepository.getAbility(name) }
        hiddenAbility = if (pokemon.hiddenAbility.isNullOrEmpty()) { Ability() } else abilityRepository.getAbility(pokemon.hiddenAbility!!)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication
                val pokemonDetailRepository = application.container.pokemonDetailRepository
                val pokemonSummaryRepository = application.container.pokemonSummaryRepository
                val moveRepository = application.container.moveRepository
                val abilityRepository = application.container.abilityRepository
                PokemonDetailViewModel(pokemonDetailRepository, pokemonSummaryRepository, moveRepository, abilityRepository, this.createSavedStateHandle())
            }
        }
    }
}
