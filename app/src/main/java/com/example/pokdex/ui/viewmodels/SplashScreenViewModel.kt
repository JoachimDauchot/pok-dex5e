package com.example.pokdex.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokdex.PokedexApplication
import com.example.pokdex.data.repositories.APIVersionRepository
import com.example.pokdex.data.repositories.MoveRepository
import com.example.pokdex.data.repositories.PokemonDetailRepository
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.example.pokdex.model.APIVersion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenViewModel(
    private val apiVersionRepository: APIVersionRepository,
    private val pokemonSummaryRepository: PokemonSummaryRepository,
    private val moveRepository: MoveRepository,
    private val pokemonDetailRepository: PokemonDetailRepository,
) : ViewModel() {

    var version: MutableStateFlow<String> = MutableStateFlow("")
    var statusText: MutableStateFlow<String> = MutableStateFlow("")
    var statusProgressText: String by mutableStateOf("")
    var statusSubtext: String by mutableStateOf("")
    var progress: Float by mutableFloatStateOf(0f)
    var isFinished: Boolean by mutableStateOf(false)
    private var summariesIndices: List<Int> by mutableStateOf(emptyList())
    init {
        getApiVersion()
    }

    private fun getApiVersion() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val apiVersion: APIVersion = apiVersionRepository.getVersion()
                version = MutableStateFlow("Dataset version: ${apiVersion.version}")
                statusText = MutableStateFlow("Checking for updates")

                if (apiVersionRepository.versionIsUpToDate() && apiVersion.wasDownloadedFully) {
                    Log.i("versionCheck", "Version is up to date and was succesfully downloaded")
                    progress = 1f
                } else {
                    Log.i("versionCheck", "Version is NOT up to date")
                    // some text to display
                    version = MutableStateFlow("Dataset version: ${apiVersionRepository.getVersion().version}")
                    statusText = MutableStateFlow("Updating summaries")
                    statusSubtext = "This may take a while on first startup"
                    // persist summaries and request the indeces for image retrieval
                    summariesIndices = pokemonSummaryRepository.refresh()
                    // retrieve images and persist to internal storage
                    val count: Int = summariesIndices.count()
                    for (index in summariesIndices) {
                        statusProgressText = "($index / $count)"
                        pokemonSummaryRepository.saveImageToInternalStorage("summary_$index.png", "$index.png")

                        progress = index.toFloat() / count.toFloat()
                    }
                    // update moves
                    version = MutableStateFlow("Updating moves set")
                    moveRepository.retrieveMoves()
                    // update abilities
                    version = MutableStateFlow("Updating abilities set")
                    // updating pokemon details
                    version = MutableStateFlow("Updating Pokemon details")
                    pokemonDetailRepository.refresh()
                    apiVersionRepository.setDownloaded(true)

                    statusText = MutableStateFlow("Download completed")
                }
                isFinished = true
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication
                val apiVersionRepository = application.container.apiVersionRepository
                val pokemonSummaryRepository = application.container.pokemonSummaryRepository
                val moveRepository = application.container.moveRepository
                val pokemonDetailRepository = application.container.pokemonDetailRepository
                SplashScreenViewModel(apiVersionRepository = apiVersionRepository, pokemonSummaryRepository, moveRepository, pokemonDetailRepository)
            }
        }
    }
}
