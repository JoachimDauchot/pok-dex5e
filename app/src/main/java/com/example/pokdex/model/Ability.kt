package com.example.pokdex.model

import com.example.pokdex.data.database.dbObjects.DbAbility

data class Ability(

    var name: String = "",
    var description: String = "",

)

fun Ability.asDbObject(): DbAbility {
    return DbAbility(name, description)
}
