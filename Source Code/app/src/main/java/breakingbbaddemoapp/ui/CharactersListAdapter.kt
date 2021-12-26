package breakingbbaddemoapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import breakingbbaddemoapp.R
import breakingbbaddemoapp.models.SimplifiedCharacterObject
import breakingbbaddemoapp.models.SinglePostDataGsonModel
import breakingbbaddemoapp.utils.StringFormatter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_feed_list_item.view.*

// Main adapter used for managing main feed list within the main Recycler View
class CharactersListAdapter(val context: Context,
                            val stringFormatter: StringFormatter = StringFormatter(),
                            val clickListener: (Int) -> Unit
) : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {

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
        val permalink = items[position].post.permalink
        val title = items[position].post.title
        val thumbnail = items[position].post.thumbnail
        val author = items[position].post.author
        val name = items[position].post.name

        // Set the data within the view
        (holder as? ItemViewHolder)?.name?.text = title
        (holder as? ItemViewHolder)?.nickname?.text = author

        // Load the picture
//        (holder as? ItemViewHolder)?.let {
//            Glide.with(context)
//                .load(pictureUrl)
//                .into(it.picture)
//        }

        // Set the onClickListener
//        (holder as? ItemViewHolder)?.container?.setOnClickListener{
//            val itemId = items[position].id
//            clickListener(itemId)
//        }
    }

    abstract class ViewHolder (view: View) : RecyclerView.ViewHolder(view)

    inner class ItemViewHolder (view: View) : ViewHolder(view) {
        val container = view.row_container
        val picture = view.imageView_picture
        val name = view.textView_name
        val nickname = view.textView_nickname
        val breakingBadSeasonAppearance = view.textView_breakingBadSeasonAppearance
        val betterCallSaulSeasonAppearance = view.textView_betterCallSaulSeasonAppearance
    }
}