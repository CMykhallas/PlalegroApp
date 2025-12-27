package com.pLg.app.ui.componentes

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import org.junit.Rule
import org.junit.Test

class ButtonComponentTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun button_click_triggers_action() {
        var clicked = false
        composeRule.setContent {
            Button(onClick = { clicked = true }, modifier = androidx.compose.ui.Modifier.testTag("test_button")) {
                Text("Clique aqui")
            }
        }

        composeRule.onNodeWithTag("test_button").performClick()
        assert(clicked)
    }
}
