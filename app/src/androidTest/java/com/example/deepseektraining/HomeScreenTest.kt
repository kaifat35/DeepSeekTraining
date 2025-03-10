package com.example.deepseektraining

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.example.deepseektraining.ui.theme.HomeScreen
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testHomeScreen() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            HomeScreen(navController)
        }

        composeTestRule.onNodeWithText("Домашний экран").assertExists()
        composeTestRule.onNodeWithText("Перейти к настройкам").performClick()
    }
}