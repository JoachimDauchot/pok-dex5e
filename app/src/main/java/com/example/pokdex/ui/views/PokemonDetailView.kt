package com.example.pokdex.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokdex.ui.viewmodels.PokemonDetailViewModel
import com.example.pokdex.ui.views.components.BusyGif
import com.example.pokdex.ui.views.components.detailComponents.PokemonDetailAbilityScore
import com.example.pokdex.ui.views.components.detailComponents.PokemonDetailEvolutions
import com.example.pokdex.ui.views.components.detailComponents.PokemonDetailMoves
import com.example.pokdex.ui.views.components.detailComponents.PokemonDetailTop

@Composable
fun PokemonDetailView(
    pokemonDetailViewModel: PokemonDetailViewModel = viewModel(factory = PokemonDetailViewModel.Factory),
    navigateToPokemon: (Int) -> Unit,
) {
    if (pokemonDetailViewModel.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.align(Alignment.Center)) {
                BusyGif()
            }
        }
    } else {
        val pokemon = pokemonDetailViewModel.pokemon
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            PokemonDetailTop(pokemon = pokemon)
            var tabIndex by remember { mutableIntStateOf(0) }
            val tabs = listOf("Attr.", "Abil.", "Moves", "Evol.", "Def")
            Column(modifier = Modifier.fillMaxWidth()) {
                TabRow(selectedTabIndex = tabIndex, divider = {}) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.White,
                        )
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                when (tabIndex) {
                    0 -> PokemonDetailAbilityScore(pokemon.attributes, pokemon.hitPoints, pokemon.armorClass)
                    1 -> Box(modifier = Modifier)
                    2 -> PokemonDetailMoves(pokemonDetailViewModel)
                    3 -> PokemonDetailEvolutions(pokemonDetailViewModel, navigateToPokemon)
                    4 -> Box(modifier = Modifier)
                }
            }
        }
    }
}
