package com.pLg.app.ui.componentes.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import com.pLg.app.ui.profile.ProfileScreen
import com.pLg.app.ui.viewmodel.UserViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test

class ProfileScreenTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun profile_screen_displays_user_data() {
        val fakeViewModel = object : UserViewModel(FakeUserRepo(), androidx.lifecycle.SavedStateHandle()) {}
        fakeViewModel.user = MutableStateFlow(com.pLg.core.model.User("u1", "Alice", 7, "pt-MZ"))

        composeRule.setContent {
            ProfileScreen(viewModel = fakeViewModel)
        }

        composeRule.onNodeWithText("Alice").assertIsDisplayed()
        composeRule.onNodeWithText("7 anos").assertIsDisplayed()
    }
}
