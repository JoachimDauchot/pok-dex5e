package com.example.pokdex.data.repositories

import android.util.Log
import com.example.pokdex.data.database.dao.AbilityDAO
import com.example.pokdex.data.database.dbObjects.asDomainObject
import com.example.pokdex.data.dtos.asDomainObject
import com.example.pokdex.data.network.AbilityService
import com.example.pokdex.data.network.getAbilitiesAsFlow
import com.example.pokdex.model.Ability
import com.example.pokdex.model.asDbObject

interface AbilityRepository {
    suspend fun insert(item: Ability)
    suspend fun getAbility(name: String): Ability

    suspend fun retrieveAbilities()
}

class PersistAbilityToDB(
    private val abilityDAO: AbilityDAO,
    private val abilityService: AbilityService,
) : AbilityRepository {
    override suspend fun insert(item: Ability) {
        abilityDAO.insert(item.asDbObject())
    }
    override suspend fun getAbility(name: String): Ability {
        return abilityDAO.getAbility(name).asDomainObject()
    }

    override suspend fun retrieveAbilities() {
        try {
            abilityService.getAbilitiesAsFlow().collect() {
                for (ability in it) {
                    Log.i("Ability", ability.name)
                    insert(ability.asDomainObject())
                }
            }
        } catch (e: Exception) {
            Log.i("API", "API is down")
        }
    }
}
