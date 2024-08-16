package com.example.pokdex.testRepositories

import com.example.pokdex.data.repositories.PokemonDetailRepository
import com.example.pokdex.model.PokemonDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TestPokemonDetailRepository : PokemonDetailRepository {
    var pokemons = mutableListOf<PokemonDetail>(
        PokemonDetail(1, "Bulbasaur"),
        PokemonDetail(4, "Charmander"),
        PokemonDetail(7, "Squirtle"),
        PokemonDetail(25, "Pikachu"),
    )
    override suspend fun insert(item: PokemonDetail) {
        pokemons.add(item)
    }

    override fun getPokemonDetail(index: Int): Flow<PokemonDetail> {
        return flow {
            emit(
                pokemons.first { pokemon -> pokemon.index == index },
            )
        }
    }

    override suspend fun refresh() {
        pokemons.clear()
        pokemons.addAll(
            listOf(
                PokemonDetail(1, "Bulbasaur"),
                PokemonDetail(4, "Charmander"),
                PokemonDetail(7, "Squirtle"),
                PokemonDetail(25, "Pikachu"),
            ),
        )
    }
}
