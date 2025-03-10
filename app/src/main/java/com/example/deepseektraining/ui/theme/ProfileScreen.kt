package com.example.deepseektraining.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import viewmodel.SettingsViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun ProfileScreen(navController: NavController, viewModel: SettingsViewModel) {
    var username by remember { mutableStateOf(viewModel.username) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Профиль пользователя", style = MaterialTheme.typography.headlineMedium)
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Имя пользователя") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            viewModel.updateUsername(username)
            navController.navigateUp()
        }) {
            Text("Сохранить")
        }
    }
}