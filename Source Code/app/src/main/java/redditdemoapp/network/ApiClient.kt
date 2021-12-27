package redditdemoapp.network

import redditdemoapp.constants.NetworkConstants
import redditdemoapp.models.PostsResponseGsonModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// External gate for communication with API endpoints (operated by Retrofit)
interface ApiClient {

    @GET(NetworkConstants.GET_SINGLE_POST_BY_ID_ENDPOINT_URL)
    fun getSinglePostById(@Path("id") id: String): Call<List<PostsResponseGsonModel>>

    @GET("/r/Android/hot.json")
    fun getFreshPosts(): Call<PostsResponseGsonModel>
}