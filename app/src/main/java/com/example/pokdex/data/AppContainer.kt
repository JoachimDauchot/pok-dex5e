package com.example.pokdex.data

import com.example.pokdex.data.network.PokemonSummaryService
import com.example.pokdex.data.repositories.PokemonSummaryDTORepository
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val pokemonSummaryRepository: PokemonSummaryRepository
}

class DefaultAppContainer() : AppContainer {
    private val baseUrl = "http://10.0.2.2:5262"
    private var retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            Json.asConverterFactory("application/json".toMediaType()),
        )
        .baseUrl(baseUrl)
        .build()

    private val pokemonSummaryService by lazy {
        retrofit.create(PokemonSummaryService::class.java)
    }
    override val pokemonSummaryRepository: PokemonSummaryRepository by lazy {
        PokemonSummaryDTORepository(pokemonSummaryService)
    }
}
