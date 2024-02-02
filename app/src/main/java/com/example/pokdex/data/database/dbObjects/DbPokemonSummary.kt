package com.example.pokdex.data.database.dbObjects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokdex.model.PokemonSummary

@Entity(tableName = "pokemonsummaries")
data class DbPokemonSummary(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var index: Int = 0,
    var name: String = "",
    var speciesRating: Float = 0.0f,
    var types: List<String> = emptyList(),
)

fun PokemonSummary.asDbPokemonSummary(): DbPokemonSummary {
    return DbPokemonSummary(index = index, name = name, speciesRating = speciesRating, types = types)
}

fun DbPokemonSummary.asDomainObject() : PokemonSummary {
    return PokemonSummary(index = index, name = name, speciesRating = speciesRating, types = types)
}

fun List<DbPokemonSummary>.asDomainObjects() : List<PokemonSummary> {
    return map{it.asDomainObject()}
}