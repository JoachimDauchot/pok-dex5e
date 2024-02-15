package com.example.pokdex.data.database.dbObjects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokdex.model.Damage
import com.example.pokdex.model.Move

@Entity(tableName = "moves")
data class DbMove(
    @PrimaryKey
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
    var damage: Map<Int, DbDamage> = emptyMap(),
)

// data classes below are saved on the move table and not seperately
data class DbDamage(
    var diceAmount: Int,
    var diceMax: Int,
    var addMoveModifier: Boolean,
)

fun DbMove.asDomainObject(): Move {
    return Move(name, description, duration, power, time, powerPoints, range, scaling, type, save, technicalMachine, isAttack, damage.mapValues { x -> x.value.asDomainObject() })
}

fun DbDamage.asDomainObject(): Damage {
    return Damage(diceAmount, diceMax, addMoveModifier)
}
