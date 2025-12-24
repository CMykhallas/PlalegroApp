package com.pLg.app.ui.strings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.pLg.app.R

object Strings {
    val titleHome: Int get() = R.string.title_home
    val titleSettings: Int get() = R.string.title_settings
    val titleAbout: Int get() = R.string.title_about
}

@Composable
fun homeTitle(): String = stringResource(id = Strings.titleHome)
