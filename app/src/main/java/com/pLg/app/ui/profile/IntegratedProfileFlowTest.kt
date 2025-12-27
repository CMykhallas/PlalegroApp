package com.pLg.app.ui.profile

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.profile.ProfileScreen
import com.pLg.app.ui.viewmodel.UserViewModel
import org.junit.Rule
import org.junit.Test

class IntegratedProfileFlowTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun full_flow_profile_displays_and_updates() {
        val repo = FakeUserRepo()
        val vm = UserViewModel(repo, androidx.lifecycle.SavedStateHandle())

        composeRule.setContent {
            ProfileScreen(viewModel = vm)
        }

        // Verifica dados iniciais
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("7 anos").assertIsDisplayed()

        // Atualiza preferências
        repo.updatePreferences(mapOf("locale" to "en-US"))

        // Ainda deve mostrar usuário
        composeRule.onNodeWithText("Alice").assertIsDisplayed()
    }
}
