package com.example.pokdex.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokdex.PokedexApplication
import com.example.pokdex.data.repositories.UserAndPkmnRepository
import com.example.pokdex.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private var userAndPkmnRepository: UserAndPkmnRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    var user: MutableStateFlow<UserProfile> = MutableStateFlow(UserProfile(0, "", 0, null))

    init {
        viewModelScope.launch {
            user.value = userAndPkmnRepository.getUser()
        }
    }
    suspend fun insertUser(name: String) {
        userAndPkmnRepository.insertUser(UserProfile(level = 1, name = name, pokemonParty = null, userId = null))
    }

    suspend fun refreshUser(){
        user.value = userAndPkmnRepository.getUser()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication

                val userAndPkmnRepository = application.container.userAndPkmnRepository
                UserProfileViewModel(userAndPkmnRepository, this.createSavedStateHandle())
            }
        }
    }
}
