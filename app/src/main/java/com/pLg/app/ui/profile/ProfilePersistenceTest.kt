package com.pLg.app.ui.profile

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
class ProfilePersistenceTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun profile_persists_after_rotation_and_restart() {
        hiltRule.inject()
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // 1. Abre perfil
        composeRule.onNodeWithText("Perfil").performClick()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()

        // 2. Rotação
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        device.setOrientationPortrait()

        // 3. Kill e restart
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // 4. Verifica se perfil ainda está acessível
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Alice").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
    }
}
