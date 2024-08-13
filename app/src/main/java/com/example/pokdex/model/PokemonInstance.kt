package com.example.pokdex.model

import com.example.pokdex.data.database.dbObjects.DbPokemonInstance

data class PokemonInstance(
    var index: Int?,
    var gameIndex: Int,
    var userHolderId: Int,
    var name: String,
    var size: String,
    var speciesRating: Float,
    var level: Int,
    var types: List<String>,
    var abilities: List<String>,
    var hiddenAbility: String?,
    var walkingSpeed: Int,
    var flyingSpeed: Int,
    var swimmingSpeed: Int,
    var climbingSpeed: Int,
    var burrowingSpeed: Int,
    var armorClass: Int,
    var currentHitPoints: Int,
    var maxHitPoints: Int,
    var hitDice: Int,
    var attributes: Attributes,
    // var moves: List<Move>,
    var savingThrows: List<String>,
    var skills: List<String>,
)

fun PokemonInstance.asDbObject(): DbPokemonInstance {
    return DbPokemonInstance(
        index = index,
        gameIndex = gameIndex,
        userHolderId = userHolderId,
        name = name,
        size = size,
        speciesRating = speciesRating,
        level = level,
        types = types,
        abilities = abilities,
        hiddenAbility = hiddenAbility,
        walkingSpeed = walkingSpeed,
        flyingSpeed = flyingSpeed,
        swimmingSpeed = swimmingSpeed,
        climbingSpeed = climbingSpeed,
        burrowingSpeed = burrowingSpeed,
        armorClass = armorClass,
        currentHitPoints = currentHitPoints,
        maxHitPoints = maxHitPoints,
        hitDice = hitDice,
        attributes = attributes.asDbObject(),
        savingThrows = savingThrows,
        skills = skills,
    )
}
