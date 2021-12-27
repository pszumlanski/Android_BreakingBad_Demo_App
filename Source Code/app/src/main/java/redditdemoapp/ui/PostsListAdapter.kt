package redditdemoapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import redditdemoapp.R
import redditdemoapp.models.SinglePostDataGsonModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_feed_list_item.view.*

// Main adapter used for managing main feed list within the main Recycler View
class PostsListAdapter(val context: Context,
                       val clickListener: (String) -> Unit
) : RecyclerView.Adapter<PostsListAdapter.ViewHolder>() {

    private var items: List<SinglePostDataGsonModel> = ArrayList()

    fun setItems(items: List<SinglePostDataGsonModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.main_feed_list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Prepare fetched data
        val title = items[position].post.title
        val author = items[position].post.author
        val thumbnail = items[position].post.thumbnail

        // Set the data within the view
        (holder as? ItemViewHolder)?.title?.text = title
        (holder as? ItemViewHolder)?.author?.text = author

        // Load the thumbnail
        val image = if (thumbnail == "self") {
            context.getString(R.string.no_thumbnail)
        } else {
            thumbnail
        }
        (holder as? ItemViewHolder)?.let {
            Glide.with(context)
                .load(image)
                .into(it.thumbnail)
        }

        // Set the onClickListener
        (holder as? ItemViewHolder)?.container?.setOnClickListener{
            val itemId = items[position].post.id
            clickListener(itemId)
        }
    }

    abstract class ViewHolder (view: View) : RecyclerView.ViewHolder(view)

    inner class ItemViewHolder (view: View) : ViewHolder(view) {
        val container = view.row_container
        val thumbnail = view.imageView_thumbnail
        val title = view.textView_title
        val author = view.textView_author
    }
}