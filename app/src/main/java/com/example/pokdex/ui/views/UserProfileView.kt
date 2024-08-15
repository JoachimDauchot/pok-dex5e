package com.example.pokdex.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokdex.ui.viewmodels.UserProfileViewModel
import com.example.pokdex.ui.views.components.BusyGif
import com.example.pokdex.ui.views.components.userProfileComponents.NameSelection
import com.example.pokdex.ui.views.components.userProfileComponents.ProfileView

@Composable
fun UserProfileView(
    userProfileViewModel: UserProfileViewModel = viewModel(factory = UserProfileViewModel.Factory),

) {
    if (userProfileViewModel.user.collectAsState().value.name.isBlank()) {
        NameSelection(userProfileViewModel = userProfileViewModel)
    } else {
        if (userProfileViewModel.isLoading.collectAsState().value) BusyGif() else ProfileView(userProfileViewModel = userProfileViewModel)
    }
}
