package com.pLg.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.pLg.app.ui.screens.AboutScreen
import com.pLg.app.ui.screens.HomeScreen
import com.pLg.app.ui.screens.SettingsScreen

@Composable
fun AppNavHost(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Home.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Destination.Home.route) {
            HomeScreen(
                windowSizeClass = windowSizeClass,
                onNavigateSettings = { navController.navigate(Destination.Settings.route) },
                onNavigateAbout = { navController.navigate(Destination.About.route) }
            )
        }
        composable(Destination.Settings.route) {
            SettingsScreen(
                windowSizeClass = windowSizeClass,
                onBack = { navController.popBackStack() }
            )
        }
        composable(Destination.About.route) {
            AboutScreen(
                windowSizeClass = windowSizeClass,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
