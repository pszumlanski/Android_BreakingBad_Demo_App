package redditdemoapp.dependencyinjection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import redditdemoapp.viewmodels.DetailedViewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import redditdemoapp.viewmodels.FeedViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    internal abstract fun bindFeedViewModel(feedViewModel: FeedViewModel)
            : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailedViewViewModel::class)
    internal abstract fun detailedViewFeedViewModel(detailedViewViewModel: DetailedViewViewModel)
            : ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory)
            : ViewModelProvider.Factory
}