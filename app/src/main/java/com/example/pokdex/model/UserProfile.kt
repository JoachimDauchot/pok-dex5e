package com.example.pokdex.model

import com.example.pokdex.data.database.dbObjects.DbUserProfile

data class UserProfile(
    var userId: Int?,
    var name: String,
    var level: Int,
    var pokemonParty: List<PokemonInstance?>?,
)

fun UserProfile.asDbObject(): DbUserProfile {
    return DbUserProfile(userId, name, level)
}
