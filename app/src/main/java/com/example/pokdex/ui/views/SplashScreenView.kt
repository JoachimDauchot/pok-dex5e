package com.example.pokdex.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokdex.ui.theme.WaterBrush
import com.example.pokdex.ui.viewmodels.SplashScreenViewModel
import com.example.pokdex.ui.views.components.BusySpinner

@Composable
fun SplashScreenView(
    splashScreenViewModel: SplashScreenViewModel = viewModel(factory = SplashScreenViewModel.Factory),
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        BusySpinner()
        LinearProgressIndicator(progress = splashScreenViewModel.progress, modifier = Modifier.fillMaxWidth().height(100.dp).padding(20.dp), color = WaterBrush)
        Text(text = splashScreenViewModel.statusText, fontSize = 30.sp)
        Text(text = splashScreenViewModel.statusProgressText, fontSize = 20.sp)
        Text(text = splashScreenViewModel.statusSubtext, fontSize = 15.sp)
        Text(text = splashScreenViewModel.version)
    }
}
