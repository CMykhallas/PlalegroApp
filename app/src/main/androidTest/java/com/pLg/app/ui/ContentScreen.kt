@Composable
fun ContentScreen(viewModel: ContentViewModel, onNavigateToUser: () -> Unit) {
    val contents by viewModel.contents.collectAsState(initial = emptyList())
    Column {
        LazyColumn {
            items(contents) { item ->
                Text(item.title)
            }
        }
        Button(onClick = onNavigateToUser) {
            Text("Perfil")
        }
    }
}
