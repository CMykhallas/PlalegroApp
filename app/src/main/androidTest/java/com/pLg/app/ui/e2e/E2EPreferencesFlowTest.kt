package com.pLg.app.ui.e2e

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class E2EPreferencesFlowTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun update_locale_preference_and_reflect_in_ui() {
        hiltRule.inject()

        // Abrir tela de preferências
        composeRule.onNodeWithText("Preferências").performClick()

        // Selecionar locale (componentes com tags: "locale_selector", "save_prefs_button")
        composeRule.onNodeWithTag("locale_selector").performClick()
        composeRule.onNodeWithText("Português (Moçambique)").performClick()
        composeRule.onNodeWithTag("save_prefs_button").performClick()

        // UI reflete a preferência
        composeRule.onNodeWithText("pt-MZ").assertIsDisplayed()

        // Voltar para conteúdo e verificar que locale permanece destacado
        composeRule.onNodeWithText("Conteúdo").performClick()
        composeRule.onNodeWithText("Idioma: pt-MZ").assertIsDisplayed()
    }
}
