package com.pLg.app.ui.preferences

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.preferences.PreferencesScreen
import com.pLg.app.ui.viewmodel.PreferencesViewModel
import org.junit.Rule
import org.junit.Test

class IntegratedPreferencesFlowTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun full_flow_preferences_update_and_back() {
        val vm = PreferencesViewModel(FakeUserRepo())
        var backCalled = false

        composeRule.setContent {
            PreferencesScreen(viewModel = vm, onBack = { backCalled = true })
        }

        composeRule.onNodeWithText("Idioma atual: pt-MZ").assertIsDisplayed()
        composeRule.onNodeWithTag("save_prefs_button").performClick()
        vm.updateLocale("en-US")

        composeRule.onNodeWithText("Voltar").performClick()
        assert(backCalled)
    }
}
