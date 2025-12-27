package com.pLg.app.ui.e2e

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.pLg.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class E2EMultiScreenFlowTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun full_login_to_content_to_profile_to_preferences_flow() {
        hiltRule.inject()

        // 1. Login
        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")
        composeRule.onNodeWithTag("login_button").performClick()

        // 2. Conteúdo
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // 3. Navegar para perfil
        composeRule.onNodeWithText("Perfil").performClick()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("7 anos").assertIsDisplayed()

        // 4. Navegar para preferências
        composeRule.onNodeWithText("Preferências").performClick()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()

        // 5. Atualizar preferências
        composeRule.onNodeWithTag("save_prefs_button").performClick()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()

        // 6. Voltar para conteúdo
        composeRule.onNodeWithText("Voltar").performClick()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // 7. Persistência após rotação
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        device.setOrientationPortrait()

        // 8. Persistência após kill/restart
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // Verifica se estado foi restaurado
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
