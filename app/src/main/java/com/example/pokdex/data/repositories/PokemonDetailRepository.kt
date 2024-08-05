package com.example.pokdex.data.repositories

import android.util.Log
import com.example.pokdex.data.database.dao.PokemonDetailDAO
import com.example.pokdex.data.database.dbObjects.asDomainObject
import com.example.pokdex.data.dtos.asDomainObject
import com.example.pokdex.data.network.PokemonService
import com.example.pokdex.data.network.getPokemonDetailsAsFlow
import com.example.pokdex.model.PokemonDetail
import com.example.pokdex.model.asDbObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface PokemonDetailRepository {
    suspend fun insert(item: PokemonDetail)
    fun getPokemonDetail(index: Int): Flow<PokemonDetail>
    suspend fun refresh()
}

class PersistPokemonDetailToDB(
    private val pokemonDetailDAO: PokemonDetailDAO,
    private val pokemonService: PokemonService,
) : PokemonDetailRepository {
    override suspend fun insert(item: PokemonDetail) {
        pokemonDetailDAO.insert(item.asDbObject())
    }

    override fun getPokemonDetail(index: Int): Flow<PokemonDetail> {
        return pokemonDetailDAO.getPokemon(index).map { it.asDomainObject() }
    }

    override suspend fun refresh() {
        try {
            pokemonService.getPokemonDetailsAsFlow().collect() {
                for (pokemon in it) {
                    insert(pokemon.asDomainObject())
                }
            }
        } catch (e: Exception) {
            Log.i("API", "API is down")
        }
    }
}
