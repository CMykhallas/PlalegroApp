package com.pLg.app.ui.e2e

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.pLg.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class E2ELoginToContentToProfileFlowTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun login_then_navigate_to_content_then_profile_and_verify_details() {
        hiltRule.inject()

        // Login (Compose TextFields com testTags: "username", "age", "login_button")
        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")
        composeRule.onNodeWithTag("login_button").performClick()

        // Conteúdo carregado (o FakeContentRepository publica itens após login)
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        composeRule.onNodeWithText("Ciências Naturais").assertIsDisplayed()

        // Navegar para perfil via botão Compose com texto "Perfil"
        composeRule.onNodeWithText("Perfil").performClick()

        // Verificar dados do perfil
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("7 anos").assertIsDisplayed()
    }
}
