package com.example.pokdex.ui.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.model.PokemonSummary
import com.example.pokdex.ui.theme.convertTypeToColor

@Composable
fun MyPokemonIndexCard(summary: PokemonSummary) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
    ) {
        Row(modifier = Modifier.padding(5.dp).fillMaxWidth()) {
            Box(modifier = Modifier.height(100.dp).width(100.dp).padding(5.dp)) {}
            Column(modifier = Modifier.width(180.dp)) {
                Text(text = summary.name, fontSize = 20.sp, color = Color.White, modifier = Modifier.padding(2.dp).align(Alignment.CenterHorizontally).fillMaxWidth())
                Text(text = "SR: ${summary.speciesRating}", modifier = Modifier.padding(2.dp).align(Alignment.CenterHorizontally).fillMaxWidth())
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (type in summary.types) {
                        Box(modifier = Modifier.padding(2.dp)) {
                            Text(text = type, fontWeight = FontWeight.ExtraBold, color = Color.Black, modifier = Modifier.background(color = convertTypeToColor(type), shape = RoundedCornerShape(12.dp)).height(25.dp).width(75.dp), textAlign = TextAlign.Center)
                        }
                    }
                }
            }
            Box(modifier = Modifier.height(100.dp).padding(5.dp)) {
                Text(text = "#${summary.index}", modifier = Modifier.align(Alignment.CenterStart), fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}
