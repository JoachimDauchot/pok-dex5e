package com.example.pokdex.data.database.dbObjects

import androidx.room.Entity
import androidx.room.PrimaryKey

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

data class DbDamage(
    var diceAmount: Int,
    var diceMax: Int,
    var addMoveModifier: Boolean,
)
