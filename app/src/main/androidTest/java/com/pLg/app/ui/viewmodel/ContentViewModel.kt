@HiltViewModel
class ContentViewModel @Inject constructor(
    private val repo: ContentRepository
) : ViewModel() {

    var fakeFlow: Flow<List<Content>>? = null

    val contents: Flow<List<Content>> =
        fakeFlow ?: repo.observeContent()
}
