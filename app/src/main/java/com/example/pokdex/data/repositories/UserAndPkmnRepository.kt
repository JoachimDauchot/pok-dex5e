package com.example.pokdex.data.repositories

import com.example.pokdex.data.database.dao.UserDAO
import com.example.pokdex.data.database.dbObjects.DbUserProfile
import com.example.pokdex.data.database.relations.asDomainObject
import com.example.pokdex.model.UserProfile
import com.example.pokdex.model.asDbObject

interface UserAndPkmnRepository {
    suspend fun insertUser(user: UserProfile)
    suspend fun getUser(): UserProfile
}

class PersistUserOrPokemonToDB(
    private val userDAO: UserDAO,
) : UserAndPkmnRepository {
    override suspend fun insertUser(user: UserProfile) {
        userDAO.insert(user.asDbObject())
    }

    override suspend fun getUser(): UserProfile {
        return userDAO.getUserProfile().asDomainObject()
    }
}
