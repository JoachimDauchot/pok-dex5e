package com.example.pokdex.data.database.dbObjects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokdex.model.Attributes
import com.example.pokdex.model.Evolve
import com.example.pokdex.model.Moves
import com.example.pokdex.model.PokemonDetail

@Entity("pokemons")
data class DbPokemonDetail(
    @PrimaryKey
    var index: Int,
    var name: String,
    var size: String,
    var speciesRating: Float,
    var minimumLevelFound: Int,
    var types: List<String>,
    var abilities: List<String>,
    var hiddenAbility: String?,
    var walkingSpeed: Int,
    var flyingSpeed: Int,
    var swimmingSpeed: Int,
    var climbingSpeed: Int,
    var burrowingSpeed: Int,
    var armorClass: Int,
    var hitPoints: Int,
    var hitDice: Int,
    var attributes: DbAttributes,
    var moves: DbMoves,
    var savingThrows: List<String>,
    var skills: List<String>,
    var evolve: DbEvolve?,
)

// data classes below are saved on the pokemonDetail table and not seperately
data class DbAttributes(
    var strength: Int,
    var dexterity: Int,
    var constitution: Int,
    var intelligence: Int,
    var wisdom: Int,
    var charisma: Int,

)
data class DbMoves(
    var level: Map<Int, List<String>>,
    var startingMoves: List<String>,
    var technicalMoves: List<Int>,
    var eggMoves: List<String>,
)
data class DbEvolve(
    var into: List<String>,
    var from: List<String>,
    var requires: List<String>,
    var currentStage: Int,
    var totalStages: Int,
    var points: Int,
    var level: Int?,
    var move: String?,
)

fun DbPokemonDetail.asDomainObject(): PokemonDetail {
    return PokemonDetail(
        index = index,
        name = name,
        size = size,
        speciesRating = speciesRating,
        minimumLevelFound = minimumLevelFound,
        types = types,
        abilities = abilities,
        hiddenAbility = hiddenAbility,
        walkingSpeed = walkingSpeed,
        flyingSpeed = flyingSpeed,
        swimmingSpeed = swimmingSpeed,
        climbingSpeed = climbingSpeed,
        burrowingSpeed = burrowingSpeed,
        armorClass = armorClass,
        hitPoints = hitPoints,
        hitDice = hitDice,
        attributes = attributes.asDomainObject(),
        moves = moves.asDomainObject(),
        savingThrows = savingThrows,
        skills = skills,
        evolve = evolve?.asDomainObject(),
    )
}

fun DbAttributes.asDomainObject(): Attributes {
    return Attributes(
        strength = strength,
        dexterity = dexterity,
        constitution = constitution,
        intelligence = intelligence,
        wisdom = wisdom,
        charisma = charisma,
    )
}

fun DbMoves.asDomainObject(): Moves {
    return Moves(
        level = level,
        startingMoves = startingMoves,
        technicalMoves = technicalMoves,
        eggMoves = eggMoves,
    )
}

fun DbEvolve.asDomainObject(): Evolve {
    return Evolve(
        into = into,
        from = from,
        requires = requires,
        currentStage = currentStage,
        totalStages = totalStages,
        points = points,
        level = level,
        move = move,
    )
}
