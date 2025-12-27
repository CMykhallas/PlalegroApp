package com.pLg.app.ui.preferences

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.preferences.PreferencesScreen
import com.pLg.app.ui.viewmodel.PreferencesViewModel
import org.junit.Rule
import org.junit.Test

class PreferencesScreenTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun preferences_screen_displays_locale_and_back_button() {
        val vm = PreferencesViewModel(FakeUserRepo())
        var backCalled = false

        composeRule.setContent {
            PreferencesScreen(viewModel = vm, onBack = { backCalled = true })
        }

        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()
        composeRule.onNodeWithTag("save_prefs_button").performClick()
        composeRule.onNodeWithText("Voltar").performClick()

        assert(backCalled)
    }
}
