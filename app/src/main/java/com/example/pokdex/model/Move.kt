package com.example.pokdex.model

import com.example.pokdex.data.database.dbObjects.DbDamage
import com.example.pokdex.data.database.dbObjects.DbMove

data class Move(

    var name: String,
    var description: String,
    var duration: String,
    var power: List<String> = emptyList(),
    var time: String,
    var powerPoints: Int,
    var range: String,
    var scaling: String = "",
    var type: String,
    var save: String = "",
    var technicalMachine: Int,
    var isAttack: Boolean,
    var damage: Map<Int, Damage> = emptyMap(),
)

data class Damage(
    var diceAmount: Int,
    var diceMax: Int,
    var addMoveModifier: Boolean,
)

fun Move.asDbObject(): DbMove {
    return DbMove(name, description, duration, power, time, powerPoints, range, scaling, type, save, technicalMachine, isAttack, damage.asDbObjects())
}

fun Damage.asDbObject(): DbDamage {
    return DbDamage(diceAmount, diceAmount, addMoveModifier)
}

fun Map<Int, Damage>.asDbObjects(): Map<Int, DbDamage> {
    return this.mapValues { x -> x.value.asDbObject() }
}
