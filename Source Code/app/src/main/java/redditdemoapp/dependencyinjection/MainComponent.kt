package redditdemoapp.dependencyinjection

import redditdemoapp.ui.DetailedViewFragment
import dagger.Component
import redditdemoapp.ui.FeedActivity
import redditdemoapp.viewmodels.DetailedViewViewModel
import redditdemoapp.viewmodels.FeedViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, FeedModule::class, ViewModelModule::class))
interface MainComponent {
    fun inject(feedActivity: FeedActivity)
    fun inject(feedFragment: DetailedViewFragment)
    fun inject(feedViewModel: FeedViewModel)
    fun inject(detailedViewViewModel: DetailedViewViewModel)
}