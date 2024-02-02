package com.example.pokdex.ui.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokdex.model.PokemonSummary
import com.example.pokdex.ui.converters.typeToIconConverter
import com.example.pokdex.ui.theme.TransparentBrush
import com.example.pokdex.ui.theme.convertTypeToColor

@Composable
fun MyPokemonIndexCard(summary: PokemonSummary, baseUrl: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { println("summary Clicked") },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),

    ) {
        Row(modifier = Modifier.padding(5.dp).fillMaxWidth()) {
            Box(modifier = Modifier.height(100.dp).width(100.dp).padding(5.dp)) {
                AsyncImage(model = "$baseUrl${summary.index}.png", contentDescription = "pokemon art", modifier = Modifier.fillMaxSize().align(Alignment.Center))
            }
            Column(modifier = Modifier.width(200.dp).height(100.dp)) {
                Text(text = summary.name, fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(2.dp).align(Alignment.CenterHorizontally).fillMaxWidth())
                Text(text = "SR: ${summary.speciesRating}", modifier = Modifier.padding(2.dp).align(Alignment.CenterHorizontally).fillMaxWidth())
                Row(modifier = Modifier.height(8.dp).fillMaxWidth()) {}
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (type in summary.types) {
                        Box(modifier = Modifier.width(100.dp).padding(PaddingValues(end = 5.dp)).background(color = convertTypeToColor(type), shape = RoundedCornerShape(12.dp))) {
                            Row(modifier = Modifier.padding(PaddingValues(start = 8.dp))) {
                                Image(
                                    painter = painterResource(id = typeToIconConverter(type)),
                                    "type",
                                    Modifier.size(15.dp).align(Alignment.CenterVertically),
                                )
                                Text(
                                    text = type,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black,
                                    modifier = Modifier.background(color = TransparentBrush).height(25.dp).align(Alignment.Bottom).padding(
                                        PaddingValues(top = 1.dp, start = 3.dp),
                                    ),
                                    textAlign = TextAlign.Left,
                                )
                            }
                        }
                    }
                }
            }

            Box(modifier = Modifier.height(100.dp).padding(5.dp)) {
                Text(text = "#${summary.index}", modifier = Modifier.align(Alignment.CenterStart), fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}
