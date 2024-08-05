package com.example.pokdex.ui.views.components.detailComponents

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.model.PokemonDetail

@Composable
fun PokemonDetailRow(pokemonDetail: PokemonDetail) {
    if (pokemonDetail.index != 0) {
        Column {
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().height(30.dp).padding(5.dp)) {
                Text(text = "Size: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = pokemonDetail.size, fontSize = 15.sp)

                Divider(color = Color.Transparent, modifier = Modifier.height(15.dp).width(1.dp).align(Alignment.CenterVertically))

                Text(text = "SR: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = pokemonDetail.speciesRating.toString(), fontSize = 15.sp)

                Divider(color = Color.Transparent, modifier = Modifier.height(15.dp).width(1.dp).align(Alignment.CenterVertically))

                Text(text = "Minimum Level: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = pokemonDetail.minimumLevelFound.toString(), fontSize = 15.sp)

                Divider(color = Color.Transparent, modifier = Modifier.height(15.dp).width(1.dp).align(Alignment.CenterVertically))

                Text(text = "ASI: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                Text(text = getASI(pokemonDetail.evolve?.totalStages), fontSize = 15.sp)
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth().height(30.dp).padding(5.dp)) {
                if (pokemonDetail.walkingSpeed > 0) {
                    Text("WSp: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    Text(pokemonDetail.walkingSpeed.toString(), fontSize = 15.sp)
                    Divider(color = Color.Transparent, modifier = Modifier.height(15.dp).width(4.dp).align(Alignment.CenterVertically))
                }
                if (pokemonDetail.swimmingSpeed > 0) {
                    Text("SwSp: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    Text(pokemonDetail.swimmingSpeed.toString(), fontSize = 15.sp)
                    Divider(color = Color.Transparent, modifier = Modifier.height(15.dp).width(4.dp).align(Alignment.CenterVertically))
                }
                if (pokemonDetail.flyingSpeed > 0) {
                    Text("FSp: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    Text(pokemonDetail.flyingSpeed.toString(), fontSize = 15.sp)
                    Divider(color = Color.Transparent, modifier = Modifier.height(15.dp).width(4.dp).align(Alignment.CenterVertically))
                }
                if (pokemonDetail.climbingSpeed > 0) {
                    Text("CSp: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    Text(pokemonDetail.climbingSpeed.toString(), fontSize = 15.sp)
                    Divider(color = Color.Transparent, modifier = Modifier.height(15.dp).width(4.dp).align(Alignment.CenterVertically))
                }
                if (pokemonDetail.burrowingSpeed > 0) {
                    Text("BSp: ", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    Text(pokemonDetail.burrowingSpeed.toString(), fontSize = 15.sp)
                    Divider(color = Color.Transparent, modifier = Modifier.height(15.dp).width(4.dp).align(Alignment.CenterVertically))
                }
            }
//
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
