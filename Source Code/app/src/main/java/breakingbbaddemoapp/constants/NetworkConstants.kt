package breakingbbaddemoapp.constants

class NetworkConstants {
    companion object {
        const val BASE_URL = "https://www.reddit.com"
        const val GET_ALL_CHARACTERS_ENDPOINT_URL = "/r/Android/hot.json"
        const val GET_SINGLE_POST_BY_ID_ENDPOINT_URL = "/r/Android/{id}.json"
    }
}
