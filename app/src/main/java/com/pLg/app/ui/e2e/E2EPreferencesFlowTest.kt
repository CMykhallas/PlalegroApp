package com.pLg.app.ui.e2e

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class E2EPreferencesFlowTest {

    @get:Rule(order = 0) val hiltRule = HiltAndroidRule(this)
    @get:Rule(order = 1) val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun update_locale_and_back_to_content() {
        hiltRule.inject()

        composeRule.onNodeWithText("Preferências").performClick()
        composeRule.onNodeWithTag("save_prefs_button").performClick()
        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()

        composeRule.onNodeWithText("Voltar").performClick()
        composeRule.onNodeWithText("Matemática Básica").assertIsDisplayed()
    }
}
