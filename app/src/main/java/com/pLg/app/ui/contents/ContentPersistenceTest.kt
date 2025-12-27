package com.pLg.app.ui.contents

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import com.pLg.app.MainActivity
import com.pLg.app.ui.viewmodel.ContentViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ContentPersistenceTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun content_persists_after_rotation_and_restart() = runTest {
        hiltRule.inject()

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // 1. Verifica conteúdo inicial
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()

        // 2. Rotação
        device.setOrientationLandscape()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
        device.setOrientationPortrait()

        // 3. Kill e restart da Activity
        device.executeShellCommand("am kill com.pLg.app")
        device.executeShellCommand("am start -n com.pLg.app/.MainActivity")

        // 4. Verifica se estado foi restaurado
        composeRule.waitUntil(timeoutMillis = 5_000) {
            composeRule.onAllNodesWithText("Matemática Básica").fetchSemanticsNodes().isNotEmpty()
        }
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
