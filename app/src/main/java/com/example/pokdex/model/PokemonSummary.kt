package com.example.pokdex.model

data class PokemonSummary(
    var name: String = "",
    var index: Int = 0,
    var speciesRating: Float = 0.0f,
    var types: List<String> = emptyList(),
)
