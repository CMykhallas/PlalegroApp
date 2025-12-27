@Composable
fun UserScreen(viewModel: UserViewModel) {
    val user by viewModel.user.collectAsState(initial = null)
    user?.let {
        Text(it.name)
    }
}
