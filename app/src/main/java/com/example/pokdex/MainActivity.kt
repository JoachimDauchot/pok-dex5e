package com.example.pokdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pokdex.ui.theme.PokédexTheme
import com.example.pokdex.ui.views.SummaryView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokédexTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SummaryView()
                }
            }
        }
    }
}
