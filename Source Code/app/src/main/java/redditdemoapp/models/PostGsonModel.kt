package redditdemoapp.models

import com.google.gson.annotations.SerializedName

// ApiResponse object used for deserializing data coming from API endpoint
data class PostGsonModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("permalink")
    val permalink: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("thumbnail")
    val thumbnail: String


)