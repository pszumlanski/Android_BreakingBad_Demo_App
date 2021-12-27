package redditdemoapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import redditdemoapp.constants.LogTags
import redditdemoapp.models.PostsResponseGsonModel
import redditdemoapp.network.ApiClient
import redditdemoapp.utils.DataFetchingCallback
import redditdemoapp.utils.FilteringTools
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FeedViewModel @Inject constructor(private val apiClient: ApiClient, private val filteringTools: FilteringTools)
    : ViewModel() {

    private var cachedAllPostsList: PostsResponseGsonModel? = null

    fun getPosts(callback: DataFetchingCallback, filterTitle: String?, filterAuthor: String?) {

        if (cachedAllPostsList != null) {
            var results = cachedAllPostsList!!
            var results2 = filteringTools.filterResults(results.data.childrenPosts, filterTitle, filterAuthor)
            callback.fetchingSuccessful(results2)
        } else {
            apiClient.getFreshPosts().enqueue(object: Callback<PostsResponseGsonModel> {

                override fun onResponse(call: Call<PostsResponseGsonModel>?,
                                        response: Response<PostsResponseGsonModel>?
                ) {
                    response?.let {
                        if (it.isSuccessful && it.body() != null) {
                            cachedAllPostsList = it.body()

                            var results = it.body()!!
                            var results2 = filteringTools.filterResults(results.data.childrenPosts, filterTitle, filterAuthor)
                            callback.fetchingSuccessful(results2)
                        } else {
                            callback.fetchingError()
                            it.errorBody()?.let {
                                Log.e(LogTags.NETWORK_ERROR, it.string())
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PostsResponseGsonModel>?, t: Throwable?) {
                    callback.fetchingError()
                    t?.let {
                        Log.e(LogTags.NETWORK_ERROR, it.message)
                    }
                }
            })
        }
    }
}