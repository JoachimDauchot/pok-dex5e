package com.example.pokdex.data.network

import com.example.pokdex.data.dtos.PokemonSummaryDTO
import retrofit2.http.GET

interface PokemonSummaryService {
    @GET("api/pokemon/summaries")
    suspend fun getSummaries(): List<PokemonSummaryDTO>
}


