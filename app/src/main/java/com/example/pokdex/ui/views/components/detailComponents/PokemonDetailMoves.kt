package com.example.pokdex.ui.views.components.detailComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.pokdex.ui.viewmodels.PokemonDetailViewModel

@Composable
fun PokemonDetailMoves(pokemonDetailViewModel: PokemonDetailViewModel) {
    Column() {
        PokemonDetailMoveSet(title = "Starting Moves", moves = pokemonDetailViewModel.startingMoves)

        for (moveSet in pokemonDetailViewModel.movesPerLevel) {
            PokemonDetailMoveSet(title = "Level: ${moveSet.key}", moves = moveSet.value)
        }
    }
//
}
