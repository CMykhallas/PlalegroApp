package org.playlearn.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.playlearn.ui.content.ContentScreen
import org.playlearn.ui.content.ContentViewModel
import org.playlearn.ui.login.LoginScreen
import org.playlearn.ui.login.LoginViewModel
import org.playlearn.ui.preferences.PreferencesScreen
import org.playlearn.ui.preferences.PreferencesViewModel
import org.playlearn.ui.profile.ProfileScreen
import org.playlearn.ui.profile.UserViewModel

object Routes {
    const val Login = "login"
    const val Content = "content"
    const val Profile = "profile"
    const val Preferences = "preferences"
}

@Composable
fun AppNav(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.Login) {
        composable(Routes.Login) {
            val vm: LoginViewModel = hiltViewModel()
            LoginScreen(vm) { navController.navigate(Routes.Content) }
        }
        composable(Routes.Content) {
            val vm: ContentViewModel = hiltViewModel()
            ContentScreen(
                viewModel = vm,
                onProfile = { navController.navigate(Routes.Profile) },
                onPrefs = { navController.navigate(Routes.Preferences) }
            )
        }
        composable(Routes.Profile) {
            val vm: UserViewModel = hiltViewModel()
            ProfileScreen(vm)
        }
        composable(Routes.Preferences) {
            val vm: PreferencesViewModel = hiltViewModel()
            PreferencesScreen(vm) { navController.popBackStack() }
        }
    }
}
