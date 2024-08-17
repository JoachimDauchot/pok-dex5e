package com.example.pokdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pokdex.ui.navigation.NavGraph
import com.example.pokdex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PokedexAppView()
                }
            }
        }
    }
}

@Composable
fun PokedexAppView(navHostController: NavHostController = rememberNavController()) {
    NavGraph.CreateNavHost(navController = navHostController)
}
