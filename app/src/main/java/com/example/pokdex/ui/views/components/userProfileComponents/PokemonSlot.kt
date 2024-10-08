package com.example.pokdex.ui.views.components.userProfileComponents

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.pokdex.model.PokemonInstance
import com.example.pokdex.ui.theme.convertTypeToColor
import com.example.pokdex.ui.viewmodels.UserProfileViewModel
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@Composable
fun PokemonSlot(pokemon: PokemonInstance, image: Bitmap?, userProfileViewModel: UserProfileViewModel) {
    var openDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    var color1: Color by remember { mutableStateOf(Color.Black) }
    var color2: Color by remember { mutableStateOf(Color.Black) }
    if (pokemon.types.count() < 2) {
        color1 = convertTypeToColor(pokemon.types[0])
        color2 = color1
    } else {
        color1 = convertTypeToColor(pokemon.types[0])
        color2 = convertTypeToColor(pokemon.types[1])
    }

    Card(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(5.dp)
            .clickable { openDialog = !openDialog }
            .border(3.dp, Brush.horizontalGradient(arrayListOf(color1, color2)), shape = RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),

    ) {
        Box(modifier = Modifier.height(200.dp).width(200.dp)) {
            AsyncImage(image, "pokemonPixelArt", modifier = Modifier.scale(8f, 8f).align(Alignment.Center), filterQuality = FilterQuality.None)
        }
    }
    when {
        openDialog -> {
            DeletePokemonDialog(
                onDismissRequest = { openDialog = false },
                deletePokemon = { coroutineScope.launch() { userProfileViewModel.deletePokemonFromParty(it) } },
                pokemon = pokemon,
            )
        }
    }
}

@Composable
fun DeletePokemonDialog(
    onDismissRequest: () -> Unit,
    deletePokemon: (PokemonInstance) -> Unit,
    pokemon: PokemonInstance,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),

    ) {
        Card(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = "Are you sure you wish to remove ${pokemon.name}? ")
                Spacer(modifier = Modifier.height(15.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(onClick = { deletePokemon(pokemon); onDismissRequest() }) {
                        Text("YES", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary, textAlign = TextAlign.Start)
                    }
                    TextButton(onClick = onDismissRequest) {
                        Text("NO", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary, textAlign = TextAlign.End)
                    }
                }
            }
        }
    }
}
