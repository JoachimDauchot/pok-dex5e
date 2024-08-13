package com.example.pokdex.model

import com.example.pokdex.data.database.dbObjects.DbAttributes
import com.example.pokdex.data.database.dbObjects.DbEvolve
import com.example.pokdex.data.database.dbObjects.DbMoves
import com.example.pokdex.data.database.dbObjects.DbPokemonDetail

data class PokemonDetail(
    var index: Int = 0,
    var name: String = "",
    var size: String = "",
    var speciesRating: Float = 0f,
    var minimumLevelFound: Int = 0,
    var types: List<String> = emptyList(),
    var abilities: List<String> = emptyList(),
    var hiddenAbility: String? = "",
    var walkingSpeed: Int = 0,
    var flyingSpeed: Int = 0,
    var swimmingSpeed: Int = 0,
    var climbingSpeed: Int = 0,
    var burrowingSpeed: Int = 0,
    var armorClass: Int = 0,
    var hitPoints: Int = 0,
    var hitDice: Int = 0,
    var attributes: Attributes = Attributes(),
    var moves: Moves = Moves(),
    var savingThrows: List<String> = emptyList(),
    var skills: List<String> = emptyList(),
    var evolve: Evolve? = Evolve(),
)

data class Attributes(
    var strength: Int = 0,
    var dexterity: Int = 0,
    var constitution: Int = 0,
    var intelligence: Int = 0,
    var wisdom: Int = 0,
    var charisma: Int = 0,

)
data class Moves(
    var level: Map<Int, List<String>> = emptyMap(),
    var startingMoves: List<String> = emptyList(),
    var technicalMoves: List<Int> = emptyList(),
    var eggMoves: List<String> = emptyList(),
)
data class Evolve(
    var into: List<String> = emptyList(),
    var from: List<String> = emptyList(),
    var requires: List<String> = emptyList(),
    var currentStage: Int = 0,
    var totalStages: Int = 0,
    var points: Int = 0,
    var level: Int? = 0,
    var move: String? = "",
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

fun PokemonDetail.asInstance(): PokemonInstance {
    return PokemonInstance(
        index = null,
        gameIndex = index,
        name = name,
        userHolderId = 0,
        speciesRating = speciesRating,
        level = 1,
        types = types,
        size = size,
        abilities = abilities,
        hiddenAbility = hiddenAbility,
        walkingSpeed = walkingSpeed,
        flyingSpeed = flyingSpeed,
        swimmingSpeed = swimmingSpeed,
        climbingSpeed = climbingSpeed,
        burrowingSpeed = burrowingSpeed,
        armorClass = armorClass,
        currentHitPoints = hitPoints,
        maxHitPoints = hitPoints,
        hitDice = hitDice,
        attributes = attributes,
        savingThrows = savingThrows,
        skills = skills,

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
        from = from,
        requires = requires,
        currentStage = currentStage,
        totalStages = totalStages,
        points = points,
        level = level,
        move = move,
    )
}
