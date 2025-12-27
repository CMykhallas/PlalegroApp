package com.pLg.app.ui.theme

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.pLg.app.MainActivity
import com.pLg.app.ui.theme.AppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ThemePersistenceTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun theme_persists_after_rotation_and_restart() {
        hiltRule.inject()
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // 1. Renderiza com tema
        composeRule.setContent {
            AppTheme {
                androidx.compose.material3.Text("Teste Persistência")
            }
        }
        composeRule.onNodeWithText("Teste Persistência").assertIsDisplayed()

        // 2. Rotação
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Teste Persistência").assertIsDisplayed()
        device.setOrientationPortrait()

        // 3. Kill e restart
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // 4. Verifica se texto ainda aparece
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Teste Persistência").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Teste Persistência").assertIsDisplayed()
    }
}
