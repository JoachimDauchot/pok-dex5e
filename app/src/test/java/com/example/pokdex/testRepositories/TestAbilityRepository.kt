package com.example.pokdex.testRepositories

import com.example.pokdex.data.repositories.AbilityRepository
import com.example.pokdex.model.Ability

class TestAbilityRepository : AbilityRepository {
    var abilities = mutableListOf<Ability>()
    override suspend fun insert(item: Ability) {
        abilities.add(item)
    }

    override suspend fun getAbility(name: String): Ability {
        return abilities.first { ability -> ability.name == name }
    }

    override suspend fun retrieveAbilities() {
        abilities.addAll(listOf(
            Ability("testAbility1", "this is a test Ability"),
            Ability("testAbility2", "this is a test Ability"),
            Ability("testAbility3", "this is a test Ability"),
            Ability("testAbility4", "this is a test Ability"),
            Ability("testAbility5", "this is a test Ability"),
        ))
    }
}
