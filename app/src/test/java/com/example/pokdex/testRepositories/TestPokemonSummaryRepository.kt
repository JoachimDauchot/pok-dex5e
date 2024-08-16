package com.example.pokdex.testRepositories

import android.graphics.Bitmap
import com.example.pokdex.data.repositories.PokemonSummaryRepository
import com.example.pokdex.model.PokemonSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestPokemonSummaryRepository : PokemonSummaryRepository {
    private var summaries = mutableListOf<PokemonSummary>(
        PokemonSummary("bulbasaur", 1, 0.5f, listOf("Grass")),
        PokemonSummary("Charmander", 4, 0.5f, listOf("Fire")),
        PokemonSummary("Squirtle", 7, 0.5f, listOf("Water")),
    )

    override suspend fun insert(item: PokemonSummary) {
        summaries.add(item)
    }

    override fun getSummaries(): Flow<List<PokemonSummary>> {
        return flow {
            emit(
                summaries,
            )
        }
    }

    override suspend fun loadSummaryImage(index: String): Bitmap {
        // images out of scope for testing
        TODO()
    }

    override suspend fun refresh() {
        summaries.clear()
        summaries.addAll(
            listOf(
                PokemonSummary("bulbasaur", 1, 0.5f, listOf("Grass")),
                PokemonSummary("Charmander", 4, 0.5f, listOf("Fire")),
                PokemonSummary("Squirtle", 7, 0.5f, listOf("Water")),
            ),
        )
    }

    override suspend fun getSummary(name: String): PokemonSummary {
        return summaries.first { summary -> summary.name == name }
    }

    override suspend fun fetchSummaryImage(index: String): Bitmap {
        // images out of scope for testing
        TODO()
    }
}
