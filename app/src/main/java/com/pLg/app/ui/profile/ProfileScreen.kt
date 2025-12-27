@Composable
fun ProfileScreen(viewModel: UserViewModel) {
    val user by viewModel.user.collectAsState(initial = null)
    user?.let {
        Column {
            Text(it.name)
            Text("${it.age} anos")
        }
    }
}
