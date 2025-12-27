package com.pLg.app.ui.componentes

import androidx.compose.material3.TextField
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import org.junit.Rule
import org.junit.Test

class TextFieldComponentTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun textfield_accepts_input() {
        composeRule.setContent {
            var text = ""
            TextField(value = text, onValueChange = { text = it }, modifier = androidx.compose.ui.Modifier.testTag("test_textfield"))
        }

        composeRule.onNodeWithTag("test_textfield").performTextInput("Olá Mundo")
        composeRule.onNodeWithTag("test_textfield").assertTextContains("Olá Mundo")
    }
}
