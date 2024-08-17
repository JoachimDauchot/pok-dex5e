package com.example.pokdex.ui.views.components.userProfileComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pokdex.ui.viewmodels.UserProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun NameSelection(userProfileViewModel: UserProfileViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        var name by remember { mutableStateOf("") }
        var isValidName by remember { mutableStateOf(true) }
        val scope = rememberCoroutineScope()

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter Name") },
            modifier = Modifier.fillMaxWidth(),

        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            isValidName = validateAndInsertProfile(name)
            if (isValidName) {
                scope.launch {
                    userProfileViewModel.insertUser(name)
                    userProfileViewModel.refreshUser()
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "OK")
        }
        Spacer(modifier = Modifier.height(15.dp))
        if (!isValidName) {
            Text(color = Color.Red, text = " Name cannot be blank or be more than 10 characters long", textAlign = TextAlign.Center)
        }
    }
}

private fun validateAndInsertProfile(name: String): Boolean {
    return name.isNotBlank() && name.length <= 10
}
