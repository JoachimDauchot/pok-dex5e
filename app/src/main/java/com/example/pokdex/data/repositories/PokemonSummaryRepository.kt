package com.example.pokdex.data.repositories

import com.example.pokdex.data.database.dao.PokemonSummaryDAO
import com.example.pokdex.data.database.dbObjects.asDbPokemonSummary
import com.example.pokdex.data.database.dbObjects.asDomainObjects
import com.example.pokdex.data.dtos.asDomainObjects
import com.example.pokdex.data.network.PokemonSummaryService
import com.example.pokdex.data.network.getSummariesAsFlow
import com.example.pokdex.model.PokemonSummary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PokemonSummaryRepository {
    suspend fun insert(item: PokemonSummary)
    fun getSummaries(): Flow<List<PokemonSummary>>

    suspend fun refresh()
}

class PersistPokemonSummaryToDb(
    private val pokemonSummaryDAO: PokemonSummaryDAO,
    private val pokemonSummaryService: PokemonSummaryService,
) : PokemonSummaryRepository {
    override suspend fun insert(item: PokemonSummary) {
        pokemonSummaryDAO.insert(item.asDbPokemonSummary())
    }
    override fun getSummaries(): Flow<List<PokemonSummary>> {
        val result = pokemonSummaryDAO.getSummaries().map {
            it.asDomainObjects()
        }
        return result
    }
    override suspend fun refresh() {
        pokemonSummaryService.getSummariesAsFlow().collect {
            for (summary in it.asDomainObjects()) {
                insert(summary)
                println(summary)
            }
        }
    }
}

// class PokemonSummaryDTORepository(
//  private val pokemonSummaryService: PokemonSummaryService,
// ) : PokemonSummaryRepository {
//    override suspend fun getSummaries() =
//       pokemonSummaryService.getSummaries().asDomainObjects()
// }
