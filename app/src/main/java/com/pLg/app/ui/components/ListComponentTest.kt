package com.pLg.app.ui.componentes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import org.junit.Rule
import org.junit.Test

class ListComponentTest {

    @get:Rule val composeRule = createComposeRule()

    @Test
    fun list_displays_items() {
        val items = listOf("Item 1", "Item 2", "Item 3")

        composeRule.setContent {
            LazyColumn {
                items(items) { item ->
                    Text(item)
                }
            }
        }

        items.forEach { item ->
            composeRule.onNodeWithText(item).assertIsDisplayed()
        }
    }
}
