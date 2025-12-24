package com.pLg.app.navigation

sealed class Destination(val route: String) {
    data object Home : Destination("home")
    data object Settings : Destination("settings")
    data object About : Destination("about")
}
