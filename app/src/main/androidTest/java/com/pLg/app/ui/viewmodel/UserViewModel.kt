@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {

    var fakeFlow: Flow<User>? = null

    val user: Flow<User?> =
        fakeFlow ?: flow { emit(repo.currentUser().getOrNull()) }
}
@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var fakeFlow: Flow<User>? = null

    val user: Flow<User?> =
        fakeFlow ?: flow {
            val id: String? = savedStateHandle["userId"]
            val current = if (id != null) repo.getUserById(id).getOrNull()
                          else repo.currentUser().getOrNull()
            emit(current)
        }
}
