package au.com.communityengagement.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.communityengagement.databinding.CommentItemBinding
import au.com.communityengagement.models.entitymodels.CommentWithUser

class CommentsAdapter(private val comments: ArrayList<CommentWithUser>) :
    RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>()
{

    inner class CommentViewHolder(private val binding: CommentItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(comment: CommentWithUser) {
            binding.apply {
                commentItem = comment
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = CommentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        comments.get(position).let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun resetData(comments: ArrayList<CommentWithUser>) {
        this.comments.clear()
        this.comments.addAll(comments)
        notifyDataSetChanged()
    }
}