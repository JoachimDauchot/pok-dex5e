package com.example.pokdex.data.dtos

import com.example.pokdex.model.Damage
import com.example.pokdex.model.Move
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveDTO(
    @SerialName("name")
    var name: String,
    @SerialName("Description")
    var description: String,
    @SerialName("Duration")
    var duration: String,
    @SerialName("Move Power")
    var power: List<String>,
    @SerialName("Move Time")
    var time: String,
    @SerialName("PP")
    var powerPoints: Int,
    @SerialName("Range")
    var range: String,
    @SerialName("Scaling")
    var scaling: String,
    @SerialName("Type")
    var type: String,
    @SerialName("Save")
    var save: String,
    @SerialName("tm")
    var technicalMachine: Int,
    @SerialName("atk")
    var attack: Boolean,
    @SerialName("Damage")
    var damage: Map<Int, DamageDTO>,
)

@Serializable
data class DamageDTO(
    @SerialName("amount")
    var diceAmount: Int,
    @SerialName("dice_max")
    var diceMax: Int,
    @SerialName("move")
    var addMoveModifier: Boolean,
)

fun MoveDTO.asDomainObject(): Move {
    return Move(
        name = name,
        description = description,
        duration = duration,
        power = power,
        time = time,
        powerPoints = powerPoints,
        range = range,
        scaling = scaling,
        type = type,
        save = save,
        technicalMachine = technicalMachine,
        isAttack = attack,
        damage = damage.asDomainObjects(),
    )
}

fun DamageDTO.asDomainObject(): Damage {
    return Damage(diceAmount = diceAmount, diceMax = diceMax, addMoveModifier = addMoveModifier)
}

fun Map<Int, DamageDTO>.asDomainObjects(): Map<Int, Damage> {
    return mapValues { x -> x.value.asDomainObject() }
}
