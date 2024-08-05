package com.example.pokdex.data.dtos

import com.example.pokdex.model.Ability
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AbilityDTO(
    @SerialName("name")
    var name: String,
    @SerialName("description")
    var description: String,

)

fun AbilityDTO.asDomainObject(): Ability {
    return Ability(name, description)
}
