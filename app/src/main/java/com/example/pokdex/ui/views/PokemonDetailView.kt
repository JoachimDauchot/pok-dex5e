package com.example.pokdex.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokdex.ui.converters.typeToIconConverter
import com.example.pokdex.ui.theme.convertTypeToColor
import com.example.pokdex.ui.viewmodels.PokemonDetailViewModel
import com.example.pokdex.ui.views.components.BusyGif
import com.example.pokdex.ui.views.components.DetailGif
import com.example.pokdex.ui.views.components.detailComponents.PokemonDetailRow

@Composable
fun PokemonDetailView(
    pokemonDetailViewModel: PokemonDetailViewModel = viewModel(factory = PokemonDetailViewModel.Factory),
) {
    val pokemon = pokemonDetailViewModel.pokemon.collectAsState()
    val colorList by remember { mutableStateOf<ArrayList<Color>>(arrayListOf()) }
    if (pokemon.value.index == 0) {
        BusyGif()
    } else {
        if (pokemon.value.types.count() < 2) {
            colorList.add(convertTypeToColor(pokemon.value.types[0]))
            colorList.add(convertTypeToColor(pokemon.value.types[0]))
        } else {
            for (type in pokemon.value.types) {
                colorList.add(convertTypeToColor(type))
            } }
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth().height(210.dp).background(Brush.horizontalGradient(colorList))) {
                Column(modifier = Modifier.align(Alignment.TopStart).padding(5.dp)) {
                    for (type in pokemon.value.types) {
                        Image(painter = painterResource(id = typeToIconConverter(type)), contentDescription = "Type", modifier = Modifier.size(25.dp).padding(top = 5.dp))
                    }
                }
                Box(
                    modifier = Modifier.fillMaxWidth().height(45.dp).align(Alignment.BottomCenter).background(color = MaterialTheme.colorScheme.background, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)),
                )
                DetailGif(
                    pokemonDetailViewModel,
                    modifier = Modifier.padding(5.dp).align(Alignment.TopCenter),
                )
            }
            Text(text = pokemon.value.name, fontSize = 45.sp, modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.ExtraBold)
            PokemonDetailRow(pokemon.value)

        }
    }
}
