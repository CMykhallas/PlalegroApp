package com.pLg.app.ui.strings

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pLg.app.R
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class LocalizationTest {

    @Test
    fun verify_portuguese_locale_strings() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = context.resources.configuration
        config.setLocale(Locale("pt", "MZ"))
        val localizedContext = context.createConfigurationContext(config)

        assertEquals("Idioma atual: pt-MZ", localizedContext.getString(R.string.current_locale))
    }
}
