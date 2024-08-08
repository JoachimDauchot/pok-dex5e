package com.example.pokdex.ui.views.components.detailComponents

import android.graphics.Bitmap
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.model.Evolve
import com.example.pokdex.ui.viewmodels.PokemonDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun PokemonDetailEvolutions(pokemonDetailViewModel: PokemonDetailViewModel, navigateToPokemon: (Int) -> Unit) {
    val evolution: Evolve? = pokemonDetailViewModel.pokemon.evolve
    val summaries = pokemonDetailViewModel.summaries
    var coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .align(Alignment.CenterHorizontally),
            Arrangement.SpaceEvenly,
        ) {
            Text(text = "Current Stage: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = if (evolution?.totalStages == 0) "1" else evolution?.currentStage.toString(), fontSize = 15.sp)

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
            Text(text = if (evolution?.totalStages == 0) "1" else evolution?.totalStages.toString(), fontSize = 15.sp)
        }
        if (summaries.containsKey("From")) {
            Text(text = "From:", modifier = Modifier.padding(5.dp))
            val from = summaries["From"]
            if (from != null) {
                for (item in from) {
                    var image: Bitmap? by remember { mutableStateOf(null) }
                    LaunchedEffect(item) {
                        withContext(Dispatchers.IO) { image = pokemonDetailViewModel.getSummaryImage(item.index.toString()) }
                    }
                    PokemonDetailEvolutionCard(summary = item, levelAt = 0, speciesRating = item.speciesRating, navigateToPokemon = navigateToPokemon, bitmap = image)
                }
            }
        }

        if (summaries.containsKey("Into")) {
            Text(text = "Into:", modifier = Modifier.padding(5.dp))
            val into = summaries["Into"]
            if (into != null) {
                for (item in into) {
                    var image: Bitmap? by remember { mutableStateOf(null) }
                    LaunchedEffect(item) {
                        withContext(Dispatchers.IO) { image = pokemonDetailViewModel.getSummaryImage(item.index.toString()) }
                    }
                    PokemonDetailEvolutionCard(summary = item, levelAt = evolution!!.level!!, speciesRating = item.speciesRating, navigateToPokemon = navigateToPokemon, bitmap = image)
                }
            }
        }
    }
}
