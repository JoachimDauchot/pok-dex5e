package com.example.pokdex.ui.views.components.userProfileComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.pokdex.ui.viewmodels.UserProfileViewModel

@Composable
fun ProfileView(userProfileViewModel: UserProfileViewModel) {
    Box() {
        Text(text = userProfileViewModel.user.collectAsState().value.name)
    }
}
