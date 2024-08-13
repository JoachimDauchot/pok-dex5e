package com.example.pokdex.ui.views.components.userProfileComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.ui.viewmodels.UserProfileViewModel

@Composable
fun ProfileView(userProfileViewModel: UserProfileViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth().height(30.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = userProfileViewModel.user.collectAsState().value.name.replaceFirstChar { it.uppercase() }, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
        }
        PokemonSlots(userProfileViewModel.user.collectAsState().value.pokemonParty, userProfileViewModel)
    }
}
