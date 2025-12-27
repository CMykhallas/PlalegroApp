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
class E2EPersistenceWithRotationAndRestartTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun state_persists_after_rotation_and_restart() {
        hiltRule.inject()

        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        device.setOrientationPortrait()

        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        composeRule.waitUntil(5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}

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
    fun full_flow_login_content_profile_preferences_rotation_restart() {
        hiltRule.inject()

        // Login
        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")
        composeRule.onNodeWithTag("login_button").performClick()

        // Conteúdo
        composeRule.waitUntil(5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // Perfil
        composeRule.onNodeWithText("Perfil").performClick()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()

        // Preferências
        composeRule.onNodeWithText("Preferências").performClick()
        composeRule.onNodeWithTag("save_prefs_button").performClick()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()

        // Voltar
        composeRule.onNodeWithText("Voltar").performClick()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

                // Rotação + Restart
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        device.setOrientationPortrait()

        // Simula kill do processo e restart da MainActivity
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // Aguarda até que o conteúdo seja novamente renderizado
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }

        // Verifica se o estado foi restaurado corretamente
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        composeRule.onNodeWithText("Perfil").assertIsDisplayed()
        composeRule.onNodeWithText("Preferências").assertIsDisplayed()
    }
}
