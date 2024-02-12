package com.example.pokdex.model

import com.example.pokdex.data.database.dbObjects.DbAttributes
import com.example.pokdex.data.database.dbObjects.DbEvolve
import com.example.pokdex.data.database.dbObjects.DbMoves
import com.example.pokdex.data.database.dbObjects.DbPokemonDetail

data class PokemonDetail(
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
    var attributes: Attributes,
    var moves: Moves,
    var savingThrows: List<String>,
    var skills: List<String>,
    var evolve: Evolve?,
)

data class Attributes(
    var strength: Int,
    var dexterity: Int,
    var constitution: Int,
    var intelligence: Int,
    var wisdom: Int,
    var charisma: Int,

)
data class Moves(
    var level: Map<Int, List<String>>,
    var startingMoves: List<String>,
    var technicalMoves: List<Int>,
    var eggMoves: List<String>,
)
data class Evolve(
    var into: List<String>,
    var requires: List<String>,
    var currentStage: Int,
    var totalStages: Int,
    var points: Int,
    var level: Int?,
    var move: String?,
)

fun PokemonDetail.asDbObject(): DbPokemonDetail {
    return DbPokemonDetail(
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
        attributes = attributes.asDbObject(),
        moves = moves.asDbObject(),
        savingThrows = savingThrows,
        skills = skills,
        evolve = evolve?.asDbObject(),
    )
}

fun Attributes.asDbObject(): DbAttributes {
    return DbAttributes(
        strength = strength,
        dexterity = dexterity,
        constitution = constitution,
        intelligence = intelligence,
        wisdom = wisdom,
        charisma = charisma,
    )
}

fun Moves.asDbObject(): DbMoves {
    return DbMoves(
        level = level,
        startingMoves = startingMoves,
        technicalMoves = technicalMoves,
        eggMoves = eggMoves,
    )
}

fun Evolve.asDbObject(): DbEvolve {
    return DbEvolve(
        into = into,
        requires = requires,
        currentStage = currentStage,
        totalStages = totalStages,
        points = points,
        level = level,
        move = move,
    )
}
