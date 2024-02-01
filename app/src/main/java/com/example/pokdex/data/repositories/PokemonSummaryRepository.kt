package com.example.pokdex.data.repositories

import com.example.pokdex.data.dtos.asDomainObjects
import com.example.pokdex.data.network.PokemonSummaryService
import com.example.pokdex.model.PokemonSummary

interface PokemonSummaryRepository {
    suspend fun getSummaries(): List<PokemonSummary>
}

class PokemonSummaryDTORepository(
    private val pokemonSummaryService: PokemonSummaryService,
) : PokemonSummaryRepository {
    override suspend fun getSummaries() =
        pokemonSummaryService.getSummaries().asDomainObjects()
}
