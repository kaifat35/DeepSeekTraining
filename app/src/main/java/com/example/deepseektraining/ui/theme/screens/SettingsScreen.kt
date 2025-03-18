package com.example.deepseektraining.ui.theme.screens

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.deepseektraining.R
import kotlinx.coroutines.launch
import viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel = viewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(stringResource(R.string.settings_screen), style = MaterialTheme.typography.headlineMedium)

        Switch(
            checked = viewModel.darkThemeEnabled,
            onCheckedChange = { enabled ->
                viewModel.toggleDarkTheme(enabled)
                scope.launch {
                    snackbarHostState.showSnackbar(
                        if (enabled) "Темная тема включена" else "Темная тема выключена"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Text(stringResource(R.string.dark_theme))

        Button(
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Настройки сохранены")
                }
                navController.navigateUp() // Вернуться на предыдущий экран
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_and_return))
        }
        SnackbarHost(hostState = snackbarHostState)
        Button(onClick = { navController.navigate("languageSettings") }) {
            Text(stringResource(R.string.Change_the_language))
        }
    }
}