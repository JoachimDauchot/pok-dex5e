package com.example.pokdex.ui.views.components.userProfileComponents

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.pokdex.model.PokemonInstance
import com.example.pokdex.ui.viewmodels.UserProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun PokemonSlotRow(pokemon1: PokemonInstance?, pokemon2: PokemonInstance?, userProfileViewModel: UserProfileViewModel) {
    var image1: Bitmap? by remember { mutableStateOf(null) }
    var image2: Bitmap? by remember { mutableStateOf(null) }
    LaunchedEffect(pokemon1, pokemon2) {
        withContext(Dispatchers.IO) {
            if (pokemon1 != null) {
                image1 = userProfileViewModel.getPokemonIcon(pokemon1.gameIndex)
            }
            if (pokemon2 != null) {
                image2 = userProfileViewModel.getPokemonIcon(pokemon2.gameIndex)
            }
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        if (pokemon1 != null) PokemonSlot(pokemon1, image1, userProfileViewModel) else AddPokemonSlot(userProfileViewModel)
        if (pokemon2 != null) PokemonSlot(pokemon2, image2, userProfileViewModel) else AddPokemonSlot(userProfileViewModel)
    }
}
