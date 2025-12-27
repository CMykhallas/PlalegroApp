package com.pLg.app.ui.preferences

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
class PreferencesPersistenceTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun preferences_persist_after_rotation_and_restart() {
        hiltRule.inject()
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // 1. Abre preferências
        composeRule.onNodeWithText("Preferências").performClick()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()

        // 2. Rotação
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()
        device.setOrientationPortrait()

        // 3. Kill e restart
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // 4. Verifica se preferências ainda estão visíveis
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Idioma atual: pt-MZ").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()
    }
}
