package com.example.pokdex.data.repositories

import com.example.pokdex.data.database.dao.PokemonInstanceDAO
import com.example.pokdex.data.database.dao.UserDAO
import com.example.pokdex.data.database.dbObjects.DbUserProfile
import com.example.pokdex.data.database.relations.asDomainObject
import com.example.pokdex.model.PokemonInstance
import com.example.pokdex.model.UserProfile
import com.example.pokdex.model.asDbObject

interface UserAndPkmnRepository {
    suspend fun insertUser(user: UserProfile)
    suspend fun getUser(): UserProfile
    suspend fun insertPokemonInstance (pokemon : PokemonInstance)
}

class PersistUserOrPokemonToDB(
    private val userDAO: UserDAO,
    private val pokemonInstanceDAO: PokemonInstanceDAO
) : UserAndPkmnRepository {
    override suspend fun insertUser(user: UserProfile) {
        userDAO.insert(user.asDbObject())
    }

    override suspend fun getUser(): UserProfile {
        return userDAO.getUserProfile().asDomainObject()
    }

    override suspend fun insertPokemonInstance(pokemon: PokemonInstance) {
        pokemonInstanceDAO.insert(pokemon.asDbObject())
    }
}
