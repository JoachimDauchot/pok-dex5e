package com.example.pokdex.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokdex.PokedexApplication
import com.example.pokdex.data.repositories.APIVersionRepository
import com.example.pokdex.data.repositories.AbilityRepository
import com.example.pokdex.data.repositories.MoveRepository
import com.example.pokdex.data.repositories.PokemonDetailRepository
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.example.pokdex.model.APIVersion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val apiVersionRepository: APIVersionRepository,
    private val pokemonSummaryRepository: PokemonSummaryRepository,
    private val moveRepository: MoveRepository,
    private val abilityRepository: AbilityRepository,
    private val pokemonDetailRepository: PokemonDetailRepository,
) : ViewModel() {

    var version: MutableStateFlow<String> = MutableStateFlow("")
    var apiVersion: MutableStateFlow<APIVersion> = MutableStateFlow(APIVersion("", false))
    var statusText: MutableStateFlow<String> = MutableStateFlow("")
    var statusProgressText: MutableStateFlow<String> = MutableStateFlow("")
    var statusSubtext: MutableStateFlow<String> = MutableStateFlow("")
    var progress: MutableStateFlow<Float> = MutableStateFlow(0f)
    var isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private var summariesIndices: List<Int> by mutableStateOf(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {

            getApiVersion()
            if (apiVersionRepository.versionIsUpToDate() && apiVersion.value.wasDownloadedFully) {
                Log.i("versionCheck", "Version is up to date and was succesfully downloaded")
                progress = MutableStateFlow(1f)
            } else {
                getSummaries()
                getMoves()
                getAbilities()
                getPokemonDetails()
                apiVersionRepository.setDownloaded(true)
            }
            isLoading.value = false
        }
    }

    private suspend fun getApiVersion() {
        apiVersion = MutableStateFlow(apiVersionRepository.getVersion())
        version.value = "Dataset version: ${apiVersion.value.version}"
        statusText.value = "Checking for updates"
    }

    private suspend fun getSummaries() {
        statusText.value = "Updating summaries"
        statusSubtext.value = "This may take a while on first startup"
        // persist summaries and request the indeces for image retrieval
        summariesIndices = pokemonSummaryRepository.refresh()
        val count: Int = summariesIndices.count()
        for (index in summariesIndices) {
            statusProgressText.value = "($index / $count)"
            pokemonSummaryRepository.saveImageToInternalStorage("summary_$index.png", "$index.png")
            progress = MutableStateFlow(index.toFloat() / count.toFloat())
        }
    }

    private suspend fun getMoves() {
        statusText.value = "Updating moves set"
        moveRepository.retrieveMoves()
    }

    private suspend fun getPokemonDetails() {
        statusText.value = "Updating Pokemon details"
        pokemonDetailRepository.refresh()
    }

    private suspend fun getAbilities() {
        statusText.value = "Updating Abilities"
        abilityRepository.retrieveAbilities()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication
                val apiVersionRepository = application.container.apiVersionRepository
                val pokemonSummaryRepository = application.container.pokemonSummaryRepository
                val moveRepository = application.container.moveRepository
                val pokemonDetailRepository = application.container.pokemonDetailRepository
                val abilityRepository = application.container.abilityRepository
                SplashScreenViewModel(apiVersionRepository = apiVersionRepository, pokemonSummaryRepository, moveRepository, abilityRepository, pokemonDetailRepository)
            }
        }
    }
}
