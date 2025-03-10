package com.example.deepseektraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deepseektraining.ui.theme.DeepSeekTrainingTheme
import com.example.deepseektraining.ui.theme.HomeScreen
import com.example.deepseektraining.ui.theme.ProfileScreen
import com.example.deepseektraining.ui.theme.SettingsScreen
import viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Получаем ViewModel
            val viewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory)

            // Используем состояние темы из ViewModel
            val darkThemeEnabled = viewModel.darkThemeEnabled

            // Применяем тему
            DeepSeekTrainingTheme(darkTheme = darkThemeEnabled) {
                DeepSeekTraining(viewModel)
            }
        }
    }
}
@Composable
fun DeepSeekTraining(viewModel: SettingsViewModel) {
    val navController = rememberNavController()

    // Настройка навигации
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController, viewModel)
        }
        composable("profile") { ProfileScreen(navController, viewModel) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DeepSeekTrainingTheme {
        HomeScreen(navController = rememberNavController())
    }
}

