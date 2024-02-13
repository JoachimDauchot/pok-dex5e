package com.example.pokdex.ui.views.components.detailComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.model.PokemonDetail

@Composable
fun PokemonDetailRow(pokemonDetail: PokemonDetail) {
    if (pokemonDetail.index != 0) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().height(25.dp)) {
            Text(text = "Size: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = pokemonDetail.size, fontSize = 15.sp)

            Divider(color = Color.White, modifier = Modifier.height(15.dp).width(1.dp).align(Alignment.CenterVertically))

            Text(text = "SR: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = pokemonDetail.speciesRating.toString(), fontSize = 15.sp)

            Divider(color = Color.White, modifier = Modifier.height(15.dp).width(1.dp).align(Alignment.CenterVertically))

            Text(text = "Minimum Level: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = pokemonDetail.minimumLevelFound.toString(), fontSize = 15.sp)

            Divider(color = Color.White, modifier = Modifier.height(15.dp).width(1.dp).align(Alignment.CenterVertically))

            Text(text = "ASI: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = getASI(pokemonDetail.evolve?.totalStages), fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
        }
    }
}

fun getASI(stages: Int?): String {
    when (stages) {
        3 -> return "2"
        2 -> return "3"
        0 -> return "4"
    }
    return "NA"
}
