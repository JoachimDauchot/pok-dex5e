package com.example.pokdex.ui.views.components.userProfileComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.R
import com.example.pokdex.ui.viewmodels.UserProfileViewModel

@Composable
fun ProfileView(userProfileViewModel: UserProfileViewModel) {
    var openDialog by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth().height(70.dp), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Top) {
            Image(painterResource(id = R.drawable.pok__ball_icon_svg), "Decoration")
            TextButton(
                onClick = { openDialog = !openDialog },
                modifier = Modifier.fillMaxHeight(),

            ) {
                Text(text = userProfileViewModel.user.collectAsState().value.name.replaceFirstChar { it.uppercase() }, color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Center)
            }

            Image(painterResource(id = R.drawable.pok__ball_icon_svg), "Decoration")
        }
        PokemonSlots(userProfileViewModel.user.collectAsState().value.pokemonParty, userProfileViewModel)
    }
}
