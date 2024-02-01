package com.example.pokdex.data.network

import com.example.pokdex.data.dtos.PokemonSummaryDTO
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface PokemonSummaryService {
    @GET("api/pokemon/summaries")
    suspend fun getSummaries(): List<PokemonSummaryDTO>
}

private var retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(
        Json.asConverterFactory("application/json".toMediaType()),
    )
    .baseUrl("http://10.0.2.2:5262")
    .build()

object PokemonSummary {
    val pokemonSummaryService by lazy {
        retrofit.create(PokemonSummaryService::class.java)
    }
}
