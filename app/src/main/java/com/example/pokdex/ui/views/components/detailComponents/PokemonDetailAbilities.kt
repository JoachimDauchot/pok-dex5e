package com.example.pokdex.ui.views.components.detailComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.ui.viewmodels.PokemonDetailViewModel

@Composable
fun PokemonDetailAbilities(pokemonDetailViewModel: PokemonDetailViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        for (ability in pokemonDetailViewModel.abilities) {
            Column(modifier = Modifier.border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(24.dp)).padding(16.dp)) {
                Row {
                    Text(fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, text = ability.name)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Text(ability.description)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (pokemonDetailViewModel.hiddenAbility.name.isNotBlank()) {
            Column(modifier = Modifier.border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(24.dp)).padding(16.dp)) {
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, text = pokemonDetailViewModel.hiddenAbility.name + " (Hidden)")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Text(pokemonDetailViewModel.hiddenAbility.description)
                }
            }
        }
    }
}
