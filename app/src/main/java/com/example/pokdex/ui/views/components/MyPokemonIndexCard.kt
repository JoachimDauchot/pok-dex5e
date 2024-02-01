package com.example.pokdex.ui.views.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyPokemonIndexCard(text: String) {
    Card(modifier = Modifier.fillMaxWidth().height(100.dp).padding(5.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)) {
        Text(text = text, modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally))
    }
}
