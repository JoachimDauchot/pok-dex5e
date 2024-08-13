package com.example.pokdex.ui.views.components.userProfileComponents

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokdex.model.PokemonInstance

@Composable
fun PokemonSlot(pokemon: PokemonInstance?, image: Bitmap?) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(5.dp)
            .clickable { /* TODO */ },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),

    ) {
        Box(modifier = Modifier.height(150.dp).width(300.dp)) {
            if (pokemon == null) {
                Button(onClick = { /*TODO*/ }) {
                }
            } else {
                AsyncImage(image, "pokemonPixelArt", modifier = Modifier.scale(8f, 8f).align(Alignment.Center), filterQuality = FilterQuality.None)
            }
        }
    }
}
