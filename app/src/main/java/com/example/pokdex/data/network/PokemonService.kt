package com.example.pokdex.data.network

import com.example.pokdex.data.dtos.PokemonDetailDTO
import com.example.pokdex.data.dtos.PokemonSummaryDTO
import kotlinx.coroutines.flow.flow
import retrofit2.http.GET

interface PokemonService {
    @GET("api/pokemon/summaries")
    suspend fun getSummaries(): List<PokemonSummaryDTO>

    @GET("api/Pokemon/pokemons")
    suspend fun getPokemonDetails(): List<PokemonDetailDTO>
}

fun PokemonService.getSummariesAsFlow() = flow { emit(getSummaries()) }
fun PokemonService.getPokemonDetailsAsFlow() = flow { emit(getPokemonDetails()) }
