package redditdemoapp.viewmodels

class DetailedViewViewModelTest {

//    private var viewModel: DetailedViewViewModel? = null
//    private var fakeSingleCharacterObject: CompleteCharacterObject? = null
//    private var fakeSingleCharacterList: List<CompleteCharacterObject>? = null
//    private var fakeSingleCharacterId: Int? = null
//
//    @Mock
//    private val apiClient: ApiClient? = null
//
//    @get:Rule
//    var rule: TestRule = InstantTaskExecutorRule()
//
//    @Before
//    fun setupTest() {
//
//        // Inject Mocks
//        MockitoAnnotations.initMocks(this)
//
//        // Initialize the ViewModel
//        viewModel = DetailedViewViewModel(apiClient!!)
//
//        // Prepare fake single character list
//        fakeSingleCharacterObject = CompleteCharacterObject(0, "John Smith", "Johnny",
//            null, listOf(3), listOf(1, 2, 3), "02.02.1990", emptyList(),
//            "Boss", "John Travolta", "Breaking Bad")
//        fakeSingleCharacterList = listOf(fakeSingleCharacterObject!!)
//
//        // Set the id of character to be used by API endpoint
//        fakeSingleCharacterId = 0
//    }
//
//    @Test
//    fun fetchSelectedCharacterByFeedViewModel() {
//
//        // Prepare API response
//        val fakeGetSelectedCharacterResponseObject = Calls.response(fakeSingleCharacterList!!)
//
//        // Set testing conditions
////        Mockito.`when`(apiClient?.getSingleCharacterById(fakeSingleCharacterId!!)).thenReturn(fakeGetSelectedCharacterResponseObject)
//
//        // Prepare fake callback
//        val fakeCallback: DataFetchingCallback = Mockito.mock(DataFetchingCallback::class.java)
//
//        // Perform the action
////        viewModel!!.getSelectedCharacter(fakeCallback, fakeSingleCharacterId!!)
//
//        // Check whether correct callback method has been called
//        Mockito.verify(fakeCallback).fetchingSuccessful(fakeSingleCharacterObject!!)
//    }
}