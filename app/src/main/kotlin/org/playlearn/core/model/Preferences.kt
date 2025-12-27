package org.playlearn.core.model

data class Preferences(
    val locale: String = "pt-MZ",
    val darkTheme: Boolean = false,
    val notificationsEnabled: Boolean = true
)
