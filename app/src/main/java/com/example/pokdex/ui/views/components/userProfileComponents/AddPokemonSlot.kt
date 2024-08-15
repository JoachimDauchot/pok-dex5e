package com.example.pokdex.ui.views.components.userProfileComponents

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.pokdex.ui.viewmodels.UserProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun AddPokemonSlot(userProfileViewModel: UserProfileViewModel) {
    var openDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(5.dp)
            .clickable { openDialog = !openDialog },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),

    ) {
        Box(modifier = Modifier.height(200.dp).width(200.dp)) {
            Icon(Icons.Rounded.Add, "Add pokemon", modifier = Modifier.scale(4f, 4f).align(Alignment.Center))
        }
    }

    when {
        openDialog -> {
            AddPokemonDialog(
                onDismissRequest = { openDialog = false },
                addPokemon = { coroutineScope.launch() { userProfileViewModel.addPokemonToParty(it) } },
            )
        }
    }
}

@Composable
fun AddPokemonDialog(
    onDismissRequest: () -> Unit,
    addPokemon: (Int) -> Unit,
) {
    var index by remember { mutableStateOf("") }
    val pattern = remember { Regex("^\\d+\$") }
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),

    ) {
        Card(modifier = Modifier.fillMaxWidth().padding(24.dp)) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = "Add index of pokemon to add")
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = index,
                    onValueChange = {
                        if (it.isEmpty() || it.matches(pattern)) {
                            index = it
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    TextButton(onClick = { addPokemon(index.toInt()); onDismissRequest() }) {
                        Text("ADD", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary, textAlign = TextAlign.Start)
                    }
                    TextButton(onClick = onDismissRequest) {
                        Text("CLOSE", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary, textAlign = TextAlign.End)
                    }
                }
            }
        }
    }
}
