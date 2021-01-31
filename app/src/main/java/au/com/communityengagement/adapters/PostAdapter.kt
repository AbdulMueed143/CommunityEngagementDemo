package au.com.communityengagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.communityengagement.R
import au.com.communityengagement.enums.PostType
import au.com.communityengagement.models.entitymodels.Post
import kotlinx.android.synthetic.main.post_list_item.view.txtContent
import kotlinx.android.synthetic.main.post_list_item.view.txtPostBy

/**
 * There are two types of posts
 *
 * 1. Is just a post
 * 2. Is announcement
 * */

class PostAdapter(private val posts: ArrayList<Post>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == PostType.POST.ordinal) {
            return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false))
        }

        return AnnouncementViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.announcement_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (posts.get(position).postType) {

            PostType.POST -> {
                (holder as PostViewHolder).bind(position)
            }

            PostType.ANNOUNCEMENT -> {
                (holder as AnnouncementViewHolder).bind(position)
            }
        }
    }

    override fun getItemCount(): Int = posts.size

    override fun getItemViewType(position: Int): Int {
        return posts.get(position).postType.ordinal
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {

            val post = posts.get(position)
            itemView.txtPostBy.setText(post.userId)
            itemView.txtContent.setText(post.content)

            //Gotta get the count of likes
            //Gotta get the comments
//            itemView.txtLikeCount
        }

    }

    inner class AnnouncementViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {
            val post = posts.get(position)
            itemView.txtPostBy.setText(post.userId)
            itemView.txtContent.setText(post.content)
        }
    }
}