package com.example.pokdex.ui.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pokdex.ui.navigation.NavGraph

@Composable
fun PokeScaffold(view: @Composable () -> Unit, navHostController: NavHostController) {
    Scaffold(bottomBar = { NavGraph.NavBar(navController = navHostController) }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            view()
        }
    }
}
