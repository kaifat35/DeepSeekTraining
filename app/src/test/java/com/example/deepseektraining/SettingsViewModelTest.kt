package com.example.deepseektraining

import com.example.deepseektraining.data.SettingsDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import com.example.deepseektraining.viewmodel.SettingsViewModel


@ExperimentalCoroutinesApi
class SettingsViewModelTest {
    @get:Rule
    private lateinit var settingsDataStore: SettingsDataStore
    private lateinit var viewModel: SettingsViewModel

    @Before
    fun setUp() {
        settingsDataStore = mock(SettingsDataStore::class.java)
        viewModel = SettingsViewModel(settingsDataStore)
    }

    @Test
    fun testToggleDarkTheme() = runTest {
        viewModel.toggleDarkTheme(true)
        assertEquals(true, viewModel.darkThemeEnabled)
    }

    @Test
    fun testUpdateUsername() = runTest {
        viewModel.updateUsername("New User")
        assertEquals("New User", viewModel.username)
    }
}