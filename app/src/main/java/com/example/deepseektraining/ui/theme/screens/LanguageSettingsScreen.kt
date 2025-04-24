@file:Suppress("DEPRECATION")

package com.example.deepseektraining.ui.theme.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deepseektraining.MainActivity
import com.example.deepseektraining.R
import com.example.deepseektraining.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun LanguageSettingsScreen(viewModel: SettingsViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.Select_a_language),
                    style = MaterialTheme.typography.headlineMedium
                )

                Button(onClick = {
                    setLocale(context, "ru")
                    scope.launch {
                        viewModel.setLanguage("ru")
                    }
                    restartActivity(context) // Перезапуск Activity
                }) {
                    Text(stringResource(R.string.Russian))
                }

                Button(onClick = {
                    setLocale(context, "en")
                    scope.launch {
                        viewModel.setLanguage("en")
                    }
                    restartActivity(context) // Перезапуск Activity
                }) {
                    Text(stringResource(R.string.English))
                }
            }
        }
    )
}


fun setLocale(context: Context, languageCode: String) {
    // Шаг 1: Создаем объект Locale
    val locale = Locale(languageCode)

    // Шаг 2: Устанавливаем локаль по умолчанию для JVM
    Locale.setDefault(locale)

    // Шаг 3: Получаем текущую конфигурацию ресурсов
    val config = Configuration(context.resources.configuration)

    // Шаг 4: Устанавливаем локаль в конфигурацию
    config.setLocale(locale)

    // Шаг 5: Создаем новый контекст с обновленной конфигурацией
    context.createConfigurationContext(config)

    // Шаг 6: Обновляем конфигурацию ресурсов приложения
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}

fun restartActivity(context: Context) {
    // Шаг 1: Создаем Intent для перезапуска MainActivity
    val intent = Intent(context, MainActivity::class.java)

    // Шаг 2: Устанавливаем флаги для Intent
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)

    // Шаг 3: Запускаем MainActivity
    context.startActivity(intent)

    // Шаг 4: Завершаем текущую Activity (если контекст является Activity)
    (context as? Activity)?.finish()
}