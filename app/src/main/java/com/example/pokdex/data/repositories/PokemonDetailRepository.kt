package com.example.pokdex.data.repositories

import com.example.pokdex.data.database.dao.PokemonDetailDAO
import com.example.pokdex.data.database.dbObjects.asDomainObject
import com.example.pokdex.data.dtos.asDomainObject
import com.example.pokdex.data.network.PokemonService
import com.example.pokdex.data.network.getPokemonDetailsAsFlow
import com.example.pokdex.model.PokemonDetail
import com.example.pokdex.model.asDbObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

interface PokemonDetailRepository {
    suspend fun insert(item: PokemonDetail)
    suspend fun getPokemonDetail(index: Int): Flow<PokemonDetail>
    suspend fun refresh()
}

class PersistPokemonDetailToDB(
    private val pokemonDetailDAO: PokemonDetailDAO,
    private val pokemonService: PokemonService,
) : PokemonDetailRepository {
    override suspend fun insert(item: PokemonDetail) {
        pokemonDetailDAO.insert(item.asDbObject())
    }

    override suspend fun getPokemonDetail(index: Int): Flow<PokemonDetail> {
        var pokemon = pokemonDetailDAO.getPokemon(index).map { it.asDomainObject() }

        return pokemon
    }

    override suspend fun refresh() {
        pokemonService.getPokemonDetailsAsFlow().collect() {
            for (pokemon in it) {
                insert(pokemon.asDomainObject())
            }
        }
    }
}
