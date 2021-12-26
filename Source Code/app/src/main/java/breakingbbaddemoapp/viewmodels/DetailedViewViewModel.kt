package breakingbbaddemoapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import breakingbbaddemoapp.constants.LogTags
import breakingbbaddemoapp.models.CompleteCharacterObject
import breakingbbaddemoapp.models.PostsResponseGsonModel
import breakingbbaddemoapp.network.ApiClient
import breakingbbaddemoapp.utils.DataFetchingCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DetailedViewViewModel @Inject constructor(private val apiClient: ApiClient)
    : ViewModel() {

    fun getSelectedCharacter(callback: DataFetchingCallback, postId: String) {
        apiClient.getSinglePostById(postId).enqueue(object: Callback<List<PostsResponseGsonModel>> {

            override fun onResponse(call: Call<List<PostsResponseGsonModel>>?,
                                    response: Response<List<PostsResponseGsonModel>>?) {
                response?.let {
                    if (it.isSuccessful && !it.body().isNullOrEmpty()) {
                        callback.fetchingSuccessful(it.body()!!.first().data.childrenPosts.first().post)
                    } else {
                        callback.fetchingError()
                        it.errorBody()?.let {
                            Log.e(LogTags.NETWORK_ERROR, it.string())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<PostsResponseGsonModel>>?, t: Throwable?) {
                callback.fetchingError()
                t?.let {
                    Log.e(LogTags.NETWORK_ERROR, it.message)
                }
            }
        })
    }
}