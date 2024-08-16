package com.example.pokdex.testRepositories

import android.graphics.Bitmap
import com.example.pokdex.data.repositories.UserAndPkmnRepository
import com.example.pokdex.model.PokemonInstance
import com.example.pokdex.model.UserProfile

class TestUserAndPokemonRepository : UserAndPkmnRepository {

    var pokemons = mutableListOf<PokemonInstance>()
    var user = UserProfile(0, "", 0, pokemons)
    override suspend fun insertUser(user: UserProfile) {
        this.user = user
    }

    override suspend fun getUser(): UserProfile {
        this.user.pokemonParty = pokemons
        return this.user
    }

    override suspend fun insertPokemonInstance(pokemon: PokemonInstance) {
        pokemons.add(pokemon)
        user.pokemonParty = pokemons
    }

    override suspend fun deletePokemonInstance(pokemon: PokemonInstance) {
        pokemons.removeIf { pkmn -> pkmn.index == pokemon.index }
    }

    override suspend fun fetchSummaryImage(index: String): Bitmap {
        TODO("Not yet implemented")
    }

    override suspend fun loadSummaryImage(index: String): Bitmap {
        TODO("Not yet implemented")
    }
}
