package redditdemoapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import redditdemoapp.models.ChildrenPostsDataGsonModel
import redditdemoapp.models.PostGsonModel
import redditdemoapp.models.PostsResponseGsonModel
import redditdemoapp.models.SinglePostDataGsonModel
import redditdemoapp.network.ApiClient
import redditdemoapp.utils.DataFetchingCallback
import retrofit2.mock.Calls

class DetailedViewViewModelTest {

    private var viewModel: DetailedViewViewModel? = null
    private var fakeSinglePostObject: SinglePostDataGsonModel? = null
    private var fakeSinglePostList: List<PostsResponseGsonModel>? = null
    private var fakeSinglePostId: String? = null

    @Mock
    private val apiClient: ApiClient? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.initMocks(this)

        // Initialize the ViewModel
        viewModel = DetailedViewViewModel(apiClient!!)

        // Prepare fake single character list
        fakeSinglePostObject = SinglePostDataGsonModel(PostGsonModel("0A", "/r/Android/1", "Your Daily Thread", "Android", "image1"))
        val fakePostList = listOf(fakeSinglePostObject!!)
        val childrenPostsDataGsonModel = ChildrenPostsDataGsonModel(fakePostList)
        fakeSinglePostList = listOf(PostsResponseGsonModel(childrenPostsDataGsonModel))

        // Set the id of character to be used by API endpoint
        fakeSinglePostId = "0A"
    }

    @Test
    fun fetchSelectedCharacterByFeedViewModel() {

        // Prepare API response
        val fakeGetSelectedPostResponseObject = Calls.response(fakeSinglePostList!!)

        // Set testing conditions
        Mockito.`when`(apiClient?.getSinglePostById(fakeSinglePostId!!)).thenReturn(fakeGetSelectedPostResponseObject)

        // Prepare fake callback
        val fakeCallback: DataFetchingCallback = Mockito.mock(DataFetchingCallback::class.java)

        // Perform the action
        viewModel!!.getSelectedPost(fakeCallback, fakeSinglePostId!!)

        // Check whether correct callback method has been called
        Mockito.verify(fakeCallback).fetchingSuccessful(fakeSinglePostList!!.first().data.childrenPosts.first().post)
    }
}