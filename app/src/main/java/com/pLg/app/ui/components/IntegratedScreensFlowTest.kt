package com.pLg.app.ui.components.screens

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class IntegratedScreensFlowTest {
    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun full_flow_login_content_profile_preferences() {
        hiltRule.inject()

        // Login
        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")
        composeRule.onNodeWithTag("login_button").performClick()

        // Conteúdo
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // Perfil
        composeRule.onNodeWithText("Perfil").performClick()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()

        // Preferências
        composeRule.onNodeWithText("Preferências").performClick()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()
    }
}
