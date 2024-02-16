package com.example.pokdex.ui.views.components.detailComponents

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.model.PokemonSummary

@Composable
fun PokemonDetailEvolutionCard(summary: PokemonSummary, levelAt: Int, navigateToPokemon: (Int) -> Unit, bitmap: Bitmap) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { navigateToPokemon(summary.index) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),

    ) {
        Row(modifier = Modifier.padding(5.dp).fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Box(modifier = Modifier.height(100.dp).width(100.dp).padding(5.dp)) {
                Image(
                    bitmap.asImageBitmap(),
                    "pokemonPixelArt",
                    modifier = Modifier.scale(8f, 8f).align(
                        Alignment.Center,
                    ),
                    filterQuality = FilterQuality.None,
                )
            }
            Column(modifier = Modifier.width(200.dp).height(100.dp)) {
                Text(
                    text = summary.name,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(2.dp).align(
                        Alignment.CenterHorizontally,
                    ).fillMaxWidth(),
                )
                Spacer(modifier = Modifier.size(15.dp))
                Text(
                    text = "min. level to evolve: $levelAt",
                    modifier = Modifier.padding(2.dp).align(
                        Alignment.CenterHorizontally,
                    ).fillMaxWidth(),
                )
            }

            Box(modifier = Modifier.height(100.dp).padding(5.dp)) {
                Text(text = "#${summary.index}", modifier = Modifier.align(Alignment.Center), fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}
