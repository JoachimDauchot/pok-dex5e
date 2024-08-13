package com.example.pokdex.data.database.dbObjects

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokdex.model.PokemonInstance

@Entity
data class DbPokemonInstance(
    @PrimaryKey(autoGenerate = true)
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
    @Embedded var attributes: DbAttributes,
    // var moves: DbMove
    var savingThrows: List<String>,
    var skills: List<String>,
)

fun DbPokemonInstance.asDomainObject(): PokemonInstance {
    return PokemonInstance(
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
        attributes = attributes.asDomainObject(),
        savingThrows = savingThrows,
        skills = skills,
    )
}
