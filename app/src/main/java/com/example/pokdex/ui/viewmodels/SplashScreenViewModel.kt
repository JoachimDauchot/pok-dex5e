package com.example.pokdex.ui.viewmodels

import android.content.Context
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
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.example.pokdex.model.APIVersion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenViewModel(
    private val apiVersionRepository: APIVersionRepository,
    private val pokemonSummaryRepository: PokemonSummaryRepository,
    private val context: Context,
) : ViewModel() {

    var version: String by mutableStateOf("")
    var statusText: String by mutableStateOf("")
    var statusProgressText: String by mutableStateOf("")
    var statusSubtext: String by mutableStateOf("")
    var progress: Float by mutableStateOf(0f)
    var summariesIndeces: List<Int> by mutableStateOf(emptyList())
    init {
        getApiVersion()
    }

    fun getApiVersion() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val apiVersion: APIVersion = apiVersionRepository.getVersion()
                if (apiVersionRepository.versionIsUpToDate() && apiVersion.wasDownloadedFully) {
                    Log.i("versionCheck", "Version is up to date and was succesfully downloaded")
                } else {
                    Log.i("versionCheck", "Version is NOT up to date")
                    // some text to display
                    version = "Dataset version: ${apiVersionRepository.getVersion().version}"
                    statusText = "Downloading summaries"
                    statusSubtext = "This may take a while on first startup"
                    // persist summaries and request the indeces for image retrieval
                    summariesIndeces = pokemonSummaryRepository.refresh(context)
                    // retrieve images and persist to internal storage
                    val count: Int = summariesIndeces.count()
                    for (index in summariesIndeces) {
                        statusProgressText = "($index / $count)"
                        pokemonSummaryRepository.saveImageToInternalStorage(context, "$index.png")

                        progress = index.toFloat() / count.toFloat()
                    }
                    apiVersionRepository.setDownloaded(true)
                    statusText = "Download completed"
                }
                version = "Dataset version: ${apiVersionRepository.getVersion().version}"
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PokedexApplication
                val apiVersionRepository = application.container.apiVersionRepository
                val pokemonSummaryRepository = application.container.pokemonSummaryRepository
                val context = application.container.context
                SplashScreenViewModel(apiVersionRepository = apiVersionRepository, pokemonSummaryRepository, context)
            }
        }
    }
}
