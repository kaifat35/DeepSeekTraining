package com.example.deepseektraining

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import androidx.compose.animation.AnimatedContent

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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DeepSeekTraining(viewModel: SettingsViewModel) {
    val navController = rememberNavController()

    // Настройка навигации
    NavHost(
        navController = navController,
        startDestination = "home" // Начальный маршрут
    ) {
        // Маршрут для HomeScreen
        composable("home") {
            AnimatedContent(
                targetState = "home",
                transitionSpec = {
                    fadeIn() togetherWith fadeOut() // Анимация по умолчанию
                }
            ) {targetState ->
                if (targetState == "home") {
                    HomeScreen(navController)
                }
            }
        }

        // Маршрут для SettingsScreen
        composable("settings") {
            AnimatedContent(
                targetState = "settings",
                transitionSpec = {
                    if (initialState == "home" && targetState == "settings") {
                        slideInHorizontally { width -> width } + fadeIn() togetherWith
                                slideOutHorizontally { width -> -width } + fadeOut()
                    } else {
                        fadeIn() togetherWith fadeOut()
                    }
                }
            ) { targetState ->
                if (targetState == "settings") {
                    SettingsScreen(navController, viewModel)
                }
            }
        }

        // Маршрут для ProfileScreen
        composable("profile") {
            AnimatedContent(
                targetState = "profile",
                transitionSpec = {
                    if (initialState == "home" && targetState == "profile") {
                        slideInVertically { height -> height } + fadeIn() togetherWith
                                slideOutVertically { height -> -height } + fadeOut()
                    } else {
                        fadeIn() togetherWith fadeOut()
                    }
                }
            ) { targetState ->
                if (targetState == "profile") {
                    ProfileScreen(navController, viewModel)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DeepSeekTrainingTheme {
        HomeScreen(navController = rememberNavController())
    }
}

