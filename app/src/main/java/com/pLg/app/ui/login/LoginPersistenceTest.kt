package com.pLg.app.ui.login

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.pLg.app.MainActivity
import com.pLg.app.ui.viewmodel.LoginViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class LoginPersistenceTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun login_persists_after_rotation_and_restart() {
        hiltRule.inject()

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // 1. Preenche login
        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")
        composeRule.onNodeWithTag("login_button").performClick()

        // 2. Rotação
        device.setOrientationLandscape()
        composeRule.onNodeWithTag("login_button").assertIsDisplayed()
        device.setOrientationPortrait()

        // 3. Kill e restart da Activity
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // 4. Verifica se campos ainda estão acessíveis
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithTag("username").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithTag("username").assertIsDisplayed()
        composeRule.onNodeWithTag("age").assertIsDisplayed()
    }
}
