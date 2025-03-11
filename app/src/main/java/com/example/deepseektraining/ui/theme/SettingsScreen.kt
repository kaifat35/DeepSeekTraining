package com.example.deepseektraining.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel = viewModel()) {
    val darkThemeEnabled = viewModel.darkThemeEnabled
    val username = viewModel.username

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Настройки", style = MaterialTheme.typography.headlineMedium)

        Switch(
            checked = darkThemeEnabled,
            onCheckedChange = { viewModel.toggleDarkTheme(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = "Темная тема")

        TextField(
            value = username,
            onValueChange = { newUsername -> viewModel.updateUsername(newUsername) },
            label = { Text("Имя пользователя") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Настройки сохранены")
                }
                navController.navigateUp() // Вернуться на предыдущий экран
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сохранить и вернуться")
        }
        SnackbarHost(hostState = snackbarHostState)
    }
}