package com.pLg.app.ui.components.screens

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
class ScreensPersistenceTest {
    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun screens_persist_after_rotation_and_restart() {
        hiltRule.inject()
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Abre conteúdo
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // Rotação
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        device.setOrientationPortrait()

        // Kill e restart
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
