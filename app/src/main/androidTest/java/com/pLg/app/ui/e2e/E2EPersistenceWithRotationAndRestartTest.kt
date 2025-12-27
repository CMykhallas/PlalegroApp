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
    fun state_persists_after_rotation_and_process_restart() {
        hiltRule.inject()

        // Pré-condição: conteúdo visível e perfil acessível
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        composeRule.onNodeWithText("Perfil").performClick()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()

        // Rotação
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        device.setOrientationPortrait()

        // Kill e restart
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // Estado restaurado
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("Matemática Básica").assertExists()
    }
}
