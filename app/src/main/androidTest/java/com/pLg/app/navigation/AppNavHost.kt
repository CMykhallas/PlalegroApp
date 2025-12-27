@Composable
fun AppNavHost(
    navController: NavHostController,
    contentViewModel: ContentViewModel,
    userViewModel: UserViewModel
) {
    NavHost(navController, startDestination = "content") {
        composable("content") {
            ContentScreen(viewModel = contentViewModel) {
                navController.navigate("user")
            }
        }
        composable("user") {
            UserScreen(viewModel = userViewModel)
        }
    }
}
