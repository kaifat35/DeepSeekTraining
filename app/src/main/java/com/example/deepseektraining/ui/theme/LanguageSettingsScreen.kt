
package com.example.deepseektraining.ui.theme

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import java.util.Locale
import androidx.compose.ui.unit.dp
import com.example.deepseektraining.MainActivity
import com.example.deepseektraining.R
import kotlinx.coroutines.launch
import viewmodel.SettingsViewModel

@Composable
fun LanguageSettingsScreen(viewModel: SettingsViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(stringResource(R.string.Select_a_language), style = MaterialTheme.typography.headlineMedium)

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


    fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun restartActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
        (context as? Activity)?.finish()
    }