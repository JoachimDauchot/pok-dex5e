package com.example.pokdex.data.network

import com.example.pokdex.data.dtos.PokemonSummaryDTO
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface PokemonSummaryService {
    @GET("api/pokemon/summaries")
    suspend fun getSummaries(): List<PokemonSummaryDTO>
}

fun PokemonSummaryService.getSummariesAsFlow() = flow { emit(getSummaries()) }
