package com.example.pokdex.ui.views

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokdex.R
import com.example.pokdex.ui.viewmodels.SplashScreenViewModel

@Composable
fun SplashScreenView(
    splashScreenViewModel: SplashScreenViewModel = viewModel(factory = SplashScreenViewModel.Factory),
) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
        ),
        label = "",
    )
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.pok__ball_icon_svg),
            contentDescription = "LoadingIcon",
            modifier = Modifier.graphicsLayer { rotationZ = angle },
        )
        LinearProgressIndicator(progress = splashScreenViewModel.progress, modifier = Modifier.fillMaxWidth().height(100.dp).padding(20.dp), color = Color.Blue)
        Text(text = splashScreenViewModel.statusText, fontSize = 30.sp)
        Text(text = splashScreenViewModel.statusSubtext, fontSize = 15.sp)
        Text(text = splashScreenViewModel.version)
    }
}
