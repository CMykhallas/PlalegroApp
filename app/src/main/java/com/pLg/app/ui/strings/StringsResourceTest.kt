package com.pLg.app.ui.strings

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pLg.app.R
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class StringsResourceTest {

    @Test
    fun verify_basic_strings() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        assertEquals("Matemática Básica", context.getString(R.string.content_math_basic))
        assertEquals("Perfil", context.getString(R.string.profile))
        assertEquals("Preferências", context.getString(R.string.preferences))
    }
}
