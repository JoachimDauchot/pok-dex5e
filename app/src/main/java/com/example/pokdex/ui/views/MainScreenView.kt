package com.example.pokdex.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokdex.ui.viewmodels.MainScreenViewModel
import com.example.pokdex.ui.views.components.MyPokemonIndexCard

@Composable
fun MainScreenView(
    viewModel: MainScreenViewModel = viewModel(),
) {
    val name by viewModel.name.collectAsState()
    val modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
    Column(modifier = modifier) {
        Text(
            text = "Pokédex 5E",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 30.sp,
        )
        TextField(value = name, onValueChange = { cv -> viewModel.changeName(cv) }, placeholder = { Text(text = "Search...") }, modifier = modifier.height(50.dp), maxLines = 1)
        Divider(thickness = 1.dp)
        MyPokemonIndexCard("This is a pokemon index card")
    }
}
