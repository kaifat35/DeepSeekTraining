package com.example.deepseektraining.ui.theme

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore(private val context: Context) {
    companion object {
        val DARK_THEME_ENABLED = booleanPreferencesKey("dark_theme_enabled")
        val USERNAME = stringPreferencesKey("username")
        val LANGUAGE = stringPreferencesKey("language")
    }

    val darkThemeEnabled: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_THEME_ENABLED] == true
        }

    val username: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[USERNAME] ?: ""
        }

    suspend fun toggleDarkTheme(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_THEME_ENABLED] = enabled
        }
    }

    suspend fun updateUsername(newUsername: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = newUsername
        }
    }


    val language: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LANGUAGE] ?: "ru" // По умолчанию русский
        }

    suspend fun updateLanguage(languageCode: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE] = languageCode
        }
    }
}