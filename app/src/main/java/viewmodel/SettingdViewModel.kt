package viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.deepseektraining.ui.theme.SettingsDataStore
import kotlinx.coroutines.launch

open class SettingsViewModel(private val settingsDataStore: SettingsDataStore) : ViewModel() {
    private val _darkThemeEnabled = mutableStateOf(false)
    open val darkThemeEnabled: Boolean get() = _darkThemeEnabled.value

    private val _username = mutableStateOf("")
    open val username: String get() = _username.value

    init {
        viewModelScope.launch {
            settingsDataStore.darkThemeEnabled.collect { enabled ->
                _darkThemeEnabled.value = enabled
            }
        }
        viewModelScope.launch {
            settingsDataStore.username.collect { name ->
                _username.value = name
            }
        }
    }

    fun toggleDarkTheme(enabled: Boolean) {
        viewModelScope.launch {
            settingsDataStore.toggleDarkTheme(enabled)
        }
    }

    fun updateUsername(newUsername: String) {
        viewModelScope.launch {
            settingsDataStore.updateUsername(newUsername)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val settingsDataStore =
                    SettingsDataStore(context = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!)
                SettingsViewModel(settingsDataStore)
            }
        }
    }
}