package redditdemoapp.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import redditdemoapp.models.PostGsonModel
import redditdemoapp.models.SinglePostDataGsonModel


class FilteringToolsTest {

    private var filteringTools: FilteringTools? = null

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setupTest() {

        // Initialize the tools
        filteringTools = FilteringTools()
    }

    @Test
    fun filterResults_titleNull() {

        // Prepare fake posts list
        val post1 = SinglePostDataGsonModel(PostGsonModel("0A", "/r/Android/1", "Your Daily Thread", "Android", "image1"))
        val post2 = SinglePostDataGsonModel(PostGsonModel("1B", "/r/Android/2", "My Daily Thread", "Studio", "image2"))
        val post3 = SinglePostDataGsonModel(PostGsonModel("2C", "/r/Android/3", "Her Daily Thread", "Reddit", "image3"))
        val postList = listOf(post1, post2, post3)

        // Prepare fake filtering values
        val filterTitle = null

        // Perform the action
        val filteredList = filteringTools!!.filterResults(postList, filterTitle)

        // Check results
        val expectedListSize = 3
        val actualListSize = filteredList.size
        Assert.assertEquals(expectedListSize, actualListSize)
    }

    @Test
    fun filterResults_filterByTitle() {

        // Prepare fake characters list
        val post1 = SinglePostDataGsonModel(PostGsonModel("0A", "/r/Android/1", "Your Daily Thread", "Android", "image1"))
        val post2 = SinglePostDataGsonModel(PostGsonModel("1B", "/r/Android/2", "My Daily Thread", "Studio", "image2"))
        val post3 = SinglePostDataGsonModel(PostGsonModel("2C", "/r/Android/3", "Her Daily Thread", "Reddit", "image3"))
        val postList = listOf(post1, post2, post3)

        // Prepare fake filtering values
        val filterTitle = "Her Daily Thread"

        // Perform the action
        val filteredList = filteringTools!!.filterResults(postList, filterTitle)

        // Check results
        val expectedListSize = 1
        val actualListSize = filteredList.size
        Assert.assertEquals(expectedListSize, actualListSize)

        val expectedFilteredPostId = "2C"
        val actualFilteredPostId = filteredList.first().post.id
        Assert.assertEquals(expectedFilteredPostId, actualFilteredPostId)
    }
}