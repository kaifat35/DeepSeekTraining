package com.example.deepseektraining.ui.theme.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import viewmodel.SettingsViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deepseektraining.R
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen(navController: NavController, viewModel: SettingsViewModel) {
    var username by remember { mutableStateOf(viewModel.username) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Column(modifier = Modifier.padding(16.dp)) {
        Text(stringResource(R.string.user_profile), style = MaterialTheme.typography.headlineMedium)
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(R.string.user_name)) },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {
            viewModel.updateUsername(username)
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
    }
}