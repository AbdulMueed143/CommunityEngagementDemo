package au.com.communityengagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import au.com.communityengagement.R
import au.com.communityengagement.databinding.AnnouncementListItemBinding
import au.com.communityengagement.databinding.PostListItemBinding
import au.com.communityengagement.enums.PostType
import au.com.communityengagement.enums.UserRole
import au.com.communityengagement.models.entitymodels.CommentWithUser
import au.com.communityengagement.models.entitymodels.DetailedPost
import au.com.communityengagement.models.entitymodels.User
import au.com.communityengagement.util.CustomSharedPreferences
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.post_list_item.view.*

/**
 * There are two types of posts
 *
 * 1. Is just a post
 * 2. Is announcement
 * */

class PostAdapter(private val posts: MutableList<DetailedPost>,
                   val customSharedPreferences: CustomSharedPreferences,
                  private val callback: iPostAdapterActions)
    :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    interface iPostAdapterActions {
        fun onLikeClicked(post: DetailedPost, userLikedIt: Boolean)
        fun onCommentClicked(post: DetailedPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == PostType.POST.ordinal) {
            return  PostViewHolder(PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        return  AnnouncementViewHolder(AnnouncementListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (posts.get(position).post.postType) {

            PostType.POST -> {
                (holder as PostViewHolder).bind(posts.get(position),
                        createOnLikeClickListener(position),
                        createOnCommentClickListener(position))
            }

            PostType.ANNOUNCEMENT -> {
                (holder as AnnouncementViewHolder).bind(posts.get(position),
                        createOnLikeClickListener(position),
                        createOnCommentClickListener(position))
            }
        }
    }

    override fun getItemCount(): Int = posts.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return posts.get(position).post.postType.ordinal
    }

    //We have created base view model class
    open class BaseViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind() {}

        fun getResourceId(post: DetailedPost, user: User?) :Int {
            user?.id?.let {
                if (post.userIsInLikedList(it) )
                    return R.drawable.ic_liked
            }

            return R.drawable.ic_like
        }
    }

    inner class PostViewHolder(val binding: PostListItemBinding) : BaseViewHolder(binding) {

        @Override fun bind(post: DetailedPost, onLikeClickListener : View.OnClickListener, onCommentClickListener : View.OnClickListener) {

            binding.apply {

                if (rcyPostComments.adapter == null) {
                    post.comments?.toCollection(ArrayList())?.let { rcyPostComments.adapter = CommentsAdapter(it) }
                }
                else {
                    //before notifying first change the posts comments
                    post.comments?.toCollection(ArrayList())?.let { (rcyPostComments.adapter as CommentsAdapter).resetData(it) }
                }

                imageResourceId = getResourceId(post, customSharedPreferences.getUser())
                onLikeClicked = onLikeClickListener
                onCommentClicked = onCommentClickListener
                detailedPostItem = post
                executePendingBindings()
            }
        }
    }

    inner class AnnouncementViewHolder(val binding: AnnouncementListItemBinding) : BaseViewHolder(binding) {
        @Override fun bind(post: DetailedPost, onLikeClickListener : View.OnClickListener, onCommentClickListener : View.OnClickListener) {

            binding.apply {

                if (rcyPostComments.adapter == null) {
                    post.comments?.toCollection(ArrayList())?.let { rcyPostComments.adapter = CommentsAdapter(it) }
                }
                else {
                    post.comments?.toCollection(ArrayList())?.let { (rcyPostComments.adapter as CommentsAdapter).resetData(it) }
                }

                imageResourceId = getResourceId(post, customSharedPreferences.getUser())
                onLikeClicked = onLikeClickListener
                onCommentClicked = onCommentClickListener
                detailedPostItem = post
                executePendingBindings()
            }
        }
    }

    private fun createOnLikeClickListener(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            posts.get(position).let {
                callback.onLikeClicked(it, posts.get(position).userIsInLikedList(customSharedPreferences.getUser()))
            }
        }
    }

    private fun createOnCommentClickListener(position: Int) : View.OnClickListener {
        return View.OnClickListener {
            posts.get(position).let {
                callback.onCommentClicked(it)
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