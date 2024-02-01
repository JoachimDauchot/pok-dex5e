package com.example.pokdex.data.dtos

import com.example.pokdex.model.PokemonSummary
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSummaryDTO(
    var index: Int = 0,
    var name: String = "",
    var speciesRating: Float = 0.0f,
    var types: List<String> = emptyList(),
)

fun List<PokemonSummaryDTO>.asDomainObjects(): List<PokemonSummary> {
    val modelList = this.map {
        PokemonSummary(it.name, it.index, it.types)
    }
    return modelList
}
