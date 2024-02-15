package com.example.pokdex.ui.views.components.detailComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pokdex.ui.viewmodels.PokemonDetailViewModel

@Composable
fun PokemonDetailMoves(pokemonDetailViewModel: PokemonDetailViewModel) {
    Column() {
        for (move in pokemonDetailViewModel.startingMoves) {
            Text(move.name)
        }
        for (move in pokemonDetailViewModel.movesPerLevel) {
            Text("LEVEL: ${move.key}")
            for (value in pokemonDetailViewModel.movesPerLevel[move.key]!!) {
                Text(value.name)
            }
        }
//
    }
}
