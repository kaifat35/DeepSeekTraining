package com.example.deepseektraining.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import com.example.deepseektraining.viewmodel.SettingsViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(48.dp)
            ) {
                Text(
                    stringResource(R.string.user_profile),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text(stringResource(R.string.user_name)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,  // Белый фон при фокусе
                        unfocusedContainerColor = Color.White,  // Белый фон без фокуса
                        disabledContainerColor = Color.White,  // Белый фон в disabled состоянии
                        focusedIndicatorColor = Color.Transparent,  // Убираем индикатор
                        unfocusedIndicatorColor = Color.Transparent,  // Убираем индикатор
                        disabledIndicatorColor = Color.Transparent  // Убираем индикатор
                    ),
                    shape = RoundedCornerShape(24.dp)  // Скругление углов
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
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
    )
}