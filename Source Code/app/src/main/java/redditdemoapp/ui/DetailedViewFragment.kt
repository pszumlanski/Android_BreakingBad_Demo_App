package redditdemoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import redditdemoapp.R
import redditdemoapp.dependencyinjection.RedditDemoApp
import redditdemoapp.models.PostGsonModel
import redditdemoapp.utils.DataFetchingCallback
import redditdemoapp.utils.StringFormatter
import redditdemoapp.viewmodels.DetailedViewViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detailed_view.*
import javax.inject.Inject

// Detailed view for displaying selected item
class DetailedViewFragment : Fragment(), DataFetchingCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailedViewViewModel

    @Inject
    lateinit var stringFormatter: StringFormatter

    private val STATE_LOADING_ERROR = "STATE_LOADING_ERROR"
    private val STATE_CONTENT_LOADED = "STATE_CONTENT_LOADED"

    init {
        RedditDemoApp.mainComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailedViewViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detailed_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Fetch selected item from the back-end and load it into the view
        fetchSelectedPost()

        // Setup Cross Button
        val closingOnClickListener = View.OnClickListener{ activity?.onBackPressed() }
        btn_cross.setOnClickListener(closingOnClickListener)

        // Setup closing on the grey fields' click
        spacing_top.setOnClickListener(closingOnClickListener)
        spacing_bottom.setOnClickListener(closingOnClickListener)
    }


    // UI setup methods

    private fun setViewState(state: String, postObject: PostGsonModel? = null) {
        when(state) {
            STATE_LOADING_ERROR -> setupLoadingErrorView()
            STATE_CONTENT_LOADED -> postObject?.let { setupContentLoadedView(it) }
        }
    }

    private fun setupLoadingErrorView() {
        (activity as? FeedActivity)?.displayErrorDialog {
            fetchSelectedPost()
        }
    }

    private fun setupContentLoadedView(postObject: PostGsonModel) {
        progressBar.visibility = View.GONE


        val thumbnail = if (postObject.thumbnail == "self") {
            requireContext().getString(R.string.no_thumbnail)
        } else {
            postObject.thumbnail
        }
        Glide.with(requireContext())
            .load(thumbnail)
            .into(imageView_thumbnail)

        textView_title.text = postObject.title
        textView_author.text = postObject.author
    }


    // Data fetching methods

    private fun fetchSelectedPost() {
        val selectedPostId = this.arguments?.getString("selectedPostId")
        selectedPostId?.let {
            viewModel.getSelectedPost(this, it)
        }
    }


    // Data Fetching Callback interface methods

    override fun fetchingSuccessful(payload: Any) {
        if ((payload as? PostGsonModel) != null) {
            setViewState(STATE_CONTENT_LOADED, payload)
        } else {
            setViewState(STATE_LOADING_ERROR)
        }
    }

    override fun fetchingError() {
        setViewState(STATE_LOADING_ERROR)
    }
}