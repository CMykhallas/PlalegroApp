package com.pLg.app.ui.login

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.pLg.app.MainActivity
import com.pLg.app.ui.viewmodel.*
import com.pLg.app.ui.login.LoginScreen
import com.pLg.app.ui.content.ContentScreen
import com.pLg.app.ui.profile.ProfileScreen
import com.pLg.app.ui.preferences.PreferencesScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class E2ELoginToContentToProfileFlowTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun full_flow_login_content_profile_preferences_with_persistence() {
        hiltRule.inject()

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // 1. Login
        composeRule.onNodeWithTag("username").performTextInput("Alice")
        composeRule.onNodeWithTag("age").performTextInput("7")
        composeRule.onNodeWithTag("login_button").performClick()

        // 2. Conteúdo
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // 3. Perfil
        composeRule.onNodeWithText("Perfil").performClick()
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("7 anos").assertIsDisplayed()

        // 4. Preferências
        composeRule.onNodeWithText("Preferências").performClick()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()
        composeRule.onNodeWithTag("save_prefs_button").performClick()

        // 5. Voltar para conteúdo
        composeRule.onNodeWithText("Voltar").performClick()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // 6. Persistência após rotação
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        device.setOrientationPortrait()

        // 7. Persistência após kill/restart
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
