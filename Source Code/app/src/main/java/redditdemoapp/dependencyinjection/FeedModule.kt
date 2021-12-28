package redditdemoapp.dependencyinjection

import dagger.Module
import dagger.Provides
import redditdemoapp.network.ApiClient
import redditdemoapp.network.ApiClientBuilder
import redditdemoapp.utils.FilteringTools
import javax.inject.Singleton

@Module
class FeedModule {

    @Provides
    @Singleton
    fun providesApiClient(): ApiClient {
        return ApiClientBuilder.apiClient()
    }

    @Provides
    @Singleton
    fun providesFilteringTools(): FilteringTools {
        return FilteringTools()
    }
}