@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepo: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<User>?>(null)
    val loginState: StateFlow<Result<User>?> = _loginState

    fun login(name: String, age: Int) {
        viewModelScope.launch {
            val user = User(id = UUID.randomUUID().toString(), name = name, age = age, locale = "pt-MZ")
            val result = userRepo.register(user)
            savedStateHandle["userId"] = user.id
            _loginState.value = result
        }
    }
}
