package com.example.pokdex.ui.views.components.detailComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.ui.viewmodels.PokemonDetailViewModel
import com.example.pokdex.ui.views.components.PokemonIndexCard

@Composable
fun PokemonDetailEvolutions(pokemonDetailViewModel: PokemonDetailViewModel, navigateToPokemon: (Int) -> Unit) {
    val evolution = pokemonDetailViewModel.pokemon.evolve
    val levelAt = pokemonDetailViewModel.pokemon.evolve!!.requires
    val summaries = pokemonDetailViewModel.summaries
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .align(Alignment.CenterHorizontally),
            Arrangement.SpaceEvenly,
        ) {
            Text(text = "Current Stage: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = evolution!!.currentStage.toString(), fontSize = 15.sp)

            Divider(
                color = Color.White,
                modifier = Modifier
                    .height(15.dp)
                    .width(2.dp)
                    .align(
                        Alignment.CenterVertically,
                    ),
            )
            Text(text = "Total Stages: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = evolution.totalStages.toString(), fontSize = 15.sp)
        }
        if (summaries.containsKey("From")) {
            Text(text = "From:", modifier = Modifier.padding(5.dp))
            val from = summaries["From"]
            val bitmap = pokemonDetailViewModel.getSummaryImage("summary_${from!!.index}.png")
            PokemonIndexCard(summary = from, bitmap = bitmap, navigateToPokemon = navigateToPokemon)
        }

        if (summaries.containsKey("Into")) {
            Text(text = "Into:", modifier = Modifier.padding(5.dp))
            val into = summaries["Into"]
            val bitmap = pokemonDetailViewModel.getSummaryImage("summary_${into!!.index}.png")
            PokemonDetailEvolutionCard(summary = into, levelAt = evolution!!.level!!, navigateToPokemon = navigateToPokemon, bitmap = bitmap)
        }
    }
}
