package au.com.communityengagement.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import au.com.communityengagement.PostActivity
import au.com.communityengagement.adapters.CommentsAdapter
import au.com.communityengagement.databinding.FragmentSelectedPostBinding
import au.com.communityengagement.di.ViewModelProviderFactory
import au.com.communityengagement.models.entitymodels.Comment
import au.com.communityengagement.models.entitymodels.CommentWithUser
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.models.viewmodels.SelectedPostViewModel
import au.com.communityengagement.util.CustomSharedPreferences
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_selected_post.*
import javax.inject.Inject

class SelectedPostFragment : DaggerFragment() {

    @Inject lateinit var viewModelProviderFactory: ViewModelProviderFactory
    @Inject lateinit var customSharedPreferences: CustomSharedPreferences
    private lateinit var viewModel : SelectedPostViewModel
    private lateinit var binding: FragmentSelectedPostBinding
    var postId : String = ""

    private var comments = ArrayList<CommentWithUser?>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(SelectedPostViewModel::class.java)

        (activity as PostActivity).intent.extras?.get(Post.TABLE_NAME)?.let {
            postId = it.toString()
        }

        viewModel.getPost(postId).observe(this, {
            it.comments?.let { commentsList -> kotlin.run {
                    comments.clear()
                    comments.addAll(commentsList)
                    setupAdapter()
                }
            }

            viewModel.user = it.user?.user
            viewModel.post = it.post
            viewModel.isLiked = it.userIsInLikedList(customSharedPreferences.getUser()?.id ?: "")

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSelectedPostBinding.inflate(inflater, container, false).apply {
            selectedPostViewModel = viewModel
        }

        return binding.root
    }


    private fun setupAdapter() {
        if (rcyComments?.adapter == null) {
            rcyComments.apply {
                adapter = CommentsAdapter(comments)
                setHasFixedSize(true)
            }
        }
        else {
            rcyComments?.adapter?.notifyDataSetChanged()
        }
    }

}