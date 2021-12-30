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
import redditdemoapp.utils.FilteringTools
import retrofit2.mock.Calls

class FeedViewModelTest {

    private var viewModel: FeedViewModel? = null
    private var fakePosts: PostsResponseGsonModel? = null

    @Mock
    private val apiClient: ApiClient? = null

    @Mock
    private val filteringTools: FilteringTools? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Inject Mocks
        MockitoAnnotations.initMocks(this)

        // Initialize the ViewModel
        viewModel = FeedViewModel(apiClient!!, filteringTools!!)

        // Prepare fake posts list
        val post1 = SinglePostDataGsonModel(PostGsonModel("0A", "/r/Android/1", "Your Daily Thread", "Android", "image1"))
        val post2 = SinglePostDataGsonModel(PostGsonModel("1B", "/r/Android/2", "My Daily Thread", "Studio", "image2"))
        val post3 = SinglePostDataGsonModel(PostGsonModel("2C", "/r/Android/3", "Her Daily Thread", "Reddit", "image3"))
        val fakePostList = listOf(post1, post2, post3)

        val childrenPostsDataGsonModel = ChildrenPostsDataGsonModel(fakePostList)
        fakePosts = PostsResponseGsonModel(childrenPostsDataGsonModel)
    }

    @Test
    fun fetchAllCharactersByFeedViewModel() {

        // Prepare API response
        val fakeGetPostsResponseObject = Calls.response(fakePosts!!)

        // Set testing conditions
        Mockito.`when`(apiClient?.getFreshPosts()).thenReturn(fakeGetPostsResponseObject)
        Mockito.`when`(filteringTools?.filterResults(fakePosts!!.data.childrenPosts, null)).thenReturn(fakePosts!!.data.childrenPosts)

        // Prepare fake callback
        val fakeCallback: DataFetchingCallback = Mockito.mock(DataFetchingCallback::class.java)

        // Perform the action
        viewModel!!.getPosts(fakeCallback, null)

        // Check whether correct callback method has been called
        Mockito.verify(fakeCallback).fetchingSuccessful(fakePosts!!.data.childrenPosts)
    }
}