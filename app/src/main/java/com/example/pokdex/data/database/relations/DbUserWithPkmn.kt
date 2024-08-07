package com.example.pokdex.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pokdex.data.database.dbObjects.DbPokemonInstance
import com.example.pokdex.data.database.dbObjects.DbUserProfile
import com.example.pokdex.data.database.dbObjects.asDomainObject
import com.example.pokdex.model.UserProfile

data class DbUserWithPkmn(
    @Embedded val userProfile: DbUserProfile?,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userHolderId",
    )
    val pokemons: List<DbPokemonInstance>?,

)

fun DbUserWithPkmn?.asDomainObject(): UserProfile {
    if (this == null) {
        return UserProfile(0, "", 0, null)
    } else {
        val pokemonParty = pokemons?.map { pokemon -> pokemon.asDomainObject() }
        return if (userProfile == null) {
            UserProfile(0, "", 0, null)
        } else {
            UserProfile(
                userId = userProfile.userId,
                level = userProfile.level!!,
                name = userProfile.name!!,
                pokemonParty = pokemonParty,
            )
        }
    }
}
