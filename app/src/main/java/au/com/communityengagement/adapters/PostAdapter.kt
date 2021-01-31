package au.com.communityengagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.communityengagement.R
import au.com.communityengagement.enums.PostType
import au.com.communityengagement.models.entitymodels.CompletePost
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.util.CustomSharedPreferences
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.post_list_item.view.*
import javax.inject.Inject

/**
 * There are two types of posts
 *
 * 1. Is just a post
 * 2. Is announcement
 * */

class PostAdapter(private val posts: MutableList<CompletePost>,
                  private val customSharedPreferences: CustomSharedPreferences) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == PostType.POST.ordinal) {
            return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false))
        }

        return AnnouncementViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.announcement_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (posts.get(position).post.postType) {

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
        return posts.get(position).post.postType.ordinal
    }

    inner class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {
            val post = posts.get(position)
            itemView.txtPostBy.setText(post.user?.name)
            itemView.txtContent.setText(post.post.content)

            val userHasLiked = post.userIsInLikedList(customSharedPreferences.getUser()?.id?:"")

            itemView.txtLikedCount.setText(getLikeCountString(post.likes?.size ?: 0, userHasLiked))

            if (userHasLiked) {
                Glide.with(itemView.context).load(R.drawable.ic_liked).into(itemView.like)
            }
            else {
                Glide.with(itemView.context).load(R.drawable.ic_like).into(itemView.like)
            }
        }
    }

    inner class AnnouncementViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {
            val post = posts.get(position)
            itemView.txtPostBy.setText(post.user?.name)
            itemView.txtContent.setText(post.post.content)

            val userHasLiked = post.userIsInLikedList(customSharedPreferences.getUser()?.id?:"")

            itemView.txtLikedCount.setText(getLikeCountString(post.likes?.size ?: 0, userHasLiked))

            if (userHasLiked) {
                Glide.with(itemView.context).load(R.drawable.ic_liked).into(itemView.like)
            }
            else {
                Glide.with(itemView.context).load(R.drawable.ic_like).into(itemView.like)
            }
        }
    }

    private fun getLikeCountString(count: Int, userIsInList: Boolean) : String {

        if(count == 0)
            return ""

        if (userIsInList && count == 1) {
            return "You have liked this post."
        }

        if (userIsInList && count > 1) {
            return "You and "+(count-1)+" other like this post."
        }

        return ""+count+"  like this post."
    }
}