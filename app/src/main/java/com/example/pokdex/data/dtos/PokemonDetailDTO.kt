package com.example.pokdex.data.dtos

import com.example.pokdex.model.Attributes
import com.example.pokdex.model.Evolve
import com.example.pokdex.model.Moves
import com.example.pokdex.model.PokemonDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailDTO(
    var index: Int,
    var name: String,
    var size: String,
    @SerialName("SR")
    var speciesRating: Float,
    @SerialName("MIN LVL FD")
    var minimumLevelFound: Int,
    @SerialName("Type")
    var types: List<String>,
    @SerialName("Abilities")
    var abilities: List<String>,
    @SerialName("Hidden Ability")
    var hiddenAbility: String?,
    @SerialName("WSp")
    var walkingSpeed: Int,
    @SerialName("Fsp")
    var flyingSpeed: Int,
    @SerialName("Ssp")
    var swimmingSpeed: Int,
    @SerialName("Climbing Speed")
    var climbingSpeed: Int,
    @SerialName("Burrowing Speed")
    var burrowingSpeed: Int,
    @SerialName("AC")
    var armorClass: Int,
    @SerialName("HP")
    var hitPoints: Int,
    @SerialName("Hit Dice")
    var hitDice: Int,
    @SerialName("attributes")
    var attributes: AttributesDTO,
    @SerialName("Moves")
    var moves: MovesDTO,
    @SerialName("saving_throws")
    var savingThrows: List<String>,
    @SerialName("Skill")
    var skills: List<String>,
    @SerialName("Evolve")
    var evolve: EvolveDTO?,
)

@Serializable
data class AttributesDTO(
    @SerialName("STR")
    var strength: Int,
    @SerialName("DEX")
    var dexterity: Int,
    @SerialName("CON")
    var constitution: Int,
    @SerialName("INT")
    var intelligence: Int,
    @SerialName("WIS")
    var wisdom: Int,
    @SerialName("CHA")
    var charisma: Int,

)

@Serializable
data class MovesDTO(
    @SerialName("Level")
    var level: Map<Int, List<String>>,
    @SerialName("Starting Moves")
    var startingMoves: List<String>,
    @SerialName("TM")
    var technicalMoves: List<Int>,
    @SerialName("egg")
    var eggMoves: List<String>,
)

@Serializable
data class EvolveDTO(
    @SerialName("into")
    var into: List<String>,
    @SerialName("from")
    var from: List<String>,
    @SerialName("requires")
    var requires: List<String>,
    @SerialName("current_stage")
    var currentStage: Int,
    @SerialName("total_stages")
    var totalStages: Int,
    @SerialName("points")
    var points: Int,
    @SerialName("level")
    var level: Int?,
    @SerialName("move")
    var move: String?,
)

fun PokemonDetailDTO.asDomainObject(): PokemonDetail {
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

fun AttributesDTO.asDomainObject(): Attributes {
    return Attributes(
        strength = strength,
        dexterity = dexterity,
        constitution = constitution,
        intelligence = intelligence,
        wisdom = wisdom,
        charisma = charisma,
    )
}

fun MovesDTO.asDomainObject(): Moves {
    return Moves(
        level = level,
        startingMoves = startingMoves,
        technicalMoves = technicalMoves,
        eggMoves = eggMoves,
    )
}

fun EvolveDTO.asDomainObject(): Evolve {
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
