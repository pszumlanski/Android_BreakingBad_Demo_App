package breakingbbaddemoapp.network

import breakingbbaddemoapp.constants.NetworkConstants
import breakingbbaddemoapp.models.CompleteCharacterObject
import breakingbbaddemoapp.models.PostsResponseGsonModel
import retrofit2.Call
import retrofit2.http.GET
import breakingbbaddemoapp.models.SimplifiedCharacterObject
import breakingbbaddemoapp.models.SinglePostDataGsonModel
import retrofit2.http.Path
import retrofit2.http.Query

// External gate for communication with API endpoints (operated by Retrofit)
interface ApiClient {

    @GET(NetworkConstants.GET_ALL_CHARACTERS_ENDPOINT_URL)
    fun getAllCharacters(): Call<List<SimplifiedCharacterObject>>

    @GET(NetworkConstants.GET_SINGLE_POST_BY_ID_ENDPOINT_URL)
    fun getSinglePostById(@Path("id") id: String): Call<List<PostsResponseGsonModel>>

    @GET("/r/Android/hot.json")
    fun getFreshPosts(): Call<PostsResponseGsonModel>
}