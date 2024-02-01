package com.example.pokdex.data.dtos

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSummaryDTO(
    var index: Int = 0,
    var name: String = "",
    var speciesRating: Float = 0.0f,
    var types: List<String> = emptyList(),
)
