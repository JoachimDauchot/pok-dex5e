package com.example.pokdex.ui.views.components.userProfileComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokdex.model.PokemonInstance
import com.example.pokdex.ui.viewmodels.UserProfileViewModel

@Composable
fun PokemonSlots(pokemonParty: List<PokemonInstance?>?, userProfileViewModel: UserProfileViewModel) {
    val pokemonPartyArray: Array<PokemonInstance?> = Array<PokemonInstance?>(6) { null }
    if (pokemonParty != null) {
        var index = 0
        for (pokemon in pokemonParty) {
            try {
                pokemonPartyArray.set(index, pokemon)
            } catch (e: ArrayIndexOutOfBoundsException) {
                continue
            }
            index++
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center) {
        PokemonSlotRow(pokemon1 = pokemonPartyArray[0], pokemon2 = pokemonPartyArray[1], userProfileViewModel)
        Spacer(modifier = Modifier.height(15.dp))
        PokemonSlotRow(pokemon1 = pokemonPartyArray[2], pokemon2 = pokemonPartyArray[3], userProfileViewModel)
        Spacer(modifier = Modifier.height(15.dp))
        PokemonSlotRow(pokemon1 = pokemonPartyArray[4], pokemon2 = pokemonPartyArray[5], userProfileViewModel)
    }
}
