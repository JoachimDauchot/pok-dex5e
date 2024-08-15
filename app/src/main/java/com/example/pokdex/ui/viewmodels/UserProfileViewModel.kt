package com.example.pokdex.ui.viewmodels

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokdex.PokedexApplication
import com.example.pokdex.data.repositories.PokemonDetailRepository
import com.example.pokdex.data.repositories.UserAndPkmnRepository
import com.example.pokdex.model.PokemonInstance
import com.example.pokdex.model.UserProfile
import com.example.pokdex.model.asInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private var userAndPkmnRepository: UserAndPkmnRepository,
    private var pokemonDetailRepository: PokemonDetailRepository,
) : ViewModel() {
    var user: MutableStateFlow<UserProfile> = MutableStateFlow(UserProfile(0, "", 0, null))
    var isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            isLoading.value = true
            user.value = userAndPkmnRepository.getUser()
            isLoading.value = false
        }
    }
    suspend fun insertUser(name: String) {
        userAndPkmnRepository.insertUser(UserProfile(level = 1, name = name, pokemonParty = null, userId = null))
    }

    suspend fun refreshUser() {
        user.value = userAndPkmnRepository.getUser()
    }

    suspend fun getPokemonIcon(index: Int): Bitmap {
        return userAndPkmnRepository.loadSummaryImage(index.toString())
    }

    suspend fun addPokemonToParty(index: Int) {
        try {
            val pokemonInstance = pokemonDetailRepository.getPokemonDetail(index).firstOrNull()?.asInstance()
            if (pokemonInstance != null && user.value.userId != 0) {
                pokemonInstance.userHolderId = user.value.userId!!
                if (user.value.pokemonParty?.size!! < 6) userAndPkmnRepository.insertPokemonInstance(pokemonInstance)
            }
            refreshUser()
        } catch (e: Exception) {
            Log.e(e.cause.toString(), e.message!!) }
    }

    suspend fun deletePokemonFromParty(pokemon: PokemonInstance) {
        try {
            userAndPkmnRepository.deletePokemonInstance(pokemon)
            refreshUser()
        } catch (e: Exception) {
            Log.e(e.cause.toString(), e.message!!) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication

                val userAndPkmnRepository = application.container.userAndPkmnRepository
                val pokemonDetailRepository = application.container.pokemonDetailRepository
                UserProfileViewModel(userAndPkmnRepository, pokemonDetailRepository)
            }
        }
    }
}
