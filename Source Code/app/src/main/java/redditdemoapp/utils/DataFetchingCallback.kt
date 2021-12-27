package redditdemoapp.utils

interface DataFetchingCallback {
    fun fetchingSuccessful(payload: Any)
    fun fetchingError()
}