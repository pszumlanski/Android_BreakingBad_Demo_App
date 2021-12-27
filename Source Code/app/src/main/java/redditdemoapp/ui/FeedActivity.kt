package redditdemoapp.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import redditdemoapp.R
import redditdemoapp.utils.DataFetchingCallback
import redditdemoapp.dependencyinjection.RedditDemoApp
import redditdemoapp.models.SinglePostDataGsonModel
import redditdemoapp.viewmodels.FeedViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_top_panel.*
import kotlinx.android.synthetic.main.loading_badge.*
import javax.inject.Inject


// Main ('feed') view
class FeedActivity : AppCompatActivity(), DataFetchingCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FeedViewModel

    private val STATE_FILTERING_IN_PROGRESS = "STATE_FILTERING_IN_PROGRESS"
    private val STATE_LOADING_ERROR = "STATE_LOADING_ERROR"
    private val STATE_CONTENT_LOADED = "STATE_CONTENT_LOADED"

    init {
        RedditDemoApp.mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(FeedViewModel::class.java)

        // Initialize RecyclerView (feed items)
        setupRecyclerView()

        // Initialize Search Panel
        setupSearchPanel()

        // Fetch feed items from the back-end and load them into the view
        fetchPosts()
    }


    // UI setup methods

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        main_feed_recyclerview.layoutManager = layoutManager
        main_feed_recyclerview.adapter = PostsListAdapter(this) { selectedPostId: String ->
            displayDetailedView(selectedPostId)
        }
    }

    private fun setupSearchPanel() {

        btn_search.setOnClickListener {
            val searchTitle = search_engine.text.toString()
            val searchAuthor = search_engine.text.toString()
            setViewState(STATE_FILTERING_IN_PROGRESS)
            updatePosts(searchTitle, searchAuthor)
        }
    }


    // UI management methods

    private fun displayDetailedView(selectedPostId: String) {
        val fragment = DetailedViewFragment()
        val bundle = Bundle()
        bundle.putString("selectedPostId", selectedPostId)
        fragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_content_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun loadItemsIntoList(items: List<SinglePostDataGsonModel>) {
        (main_feed_recyclerview.adapter as PostsListAdapter).setItems(items)
    }

    fun displayErrorDialog(tryAgainAction: () -> Unit) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.loading_problem_check_the_internet_connection)
        builder.setPositiveButton(R.string.try_again) { _, _ ->
            tryAgainAction()
        }
        builder.create().show()
    }

    private fun setViewState(state: String) {
        when(state) {
            STATE_FILTERING_IN_PROGRESS -> setupLoadingView()
            STATE_LOADING_ERROR -> setupLoadingErrorView()
            STATE_CONTENT_LOADED -> setupContentLoadedView()
        }
    }

    private fun setupLoadingView() {
        btn_search.isEnabled = false
    }

    private fun setupLoadingErrorView() {
        displayErrorDialog {
            fetchPosts()
        }
    }

    private fun setupContentLoadedView() {
        loading_container.visibility = View.GONE
        btn_search.isEnabled = true
    }


    // Data fetching methods

    private fun fetchPosts() {
        viewModel.getPosts(this, null, null)
    }

    private fun updatePosts(filterTitle: String?, filterAuthor: String?) {
        viewModel.getPosts(this, filterTitle, filterAuthor)
    }


    // Data Fetching Callback interface methods

    override fun fetchingSuccessful(payload: Any) {
        if ((payload as? List<SinglePostDataGsonModel>) != null) {
            loadItemsIntoList(payload)
            setViewState(STATE_CONTENT_LOADED)
        } else {
            setViewState(STATE_LOADING_ERROR)
        }
    }

    override fun fetchingError() {
        setViewState(STATE_LOADING_ERROR)
    }
}