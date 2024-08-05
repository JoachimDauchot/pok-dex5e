package com.example.pokdex.data.database.dbObjects

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokdex.model.Ability

@Entity(tableName = "abilities")
data class DbAbility(
    @PrimaryKey
    var name: String,
    var description: String,
)

fun DbAbility.asDomainObject(): Ability {
    return Ability(name, description)
}
