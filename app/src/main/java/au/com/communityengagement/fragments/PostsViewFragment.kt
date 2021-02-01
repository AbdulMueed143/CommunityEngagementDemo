package au.com.communityengagement.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import au.com.communityengagement.PostActivity
import au.com.communityengagement.adapters.PostAdapter
import au.com.communityengagement.databinding.FragmentPostViewBinding
import au.com.communityengagement.di.ViewModelProviderFactory
import au.com.communityengagement.models.entitymodels.DetailedPost
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.models.viewmodels.PostsViewModel
import au.com.communityengagement.util.CustomSharedPreferences
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post_view.*
import javax.inject.Inject

/**
 * This is general fragment
 * We can simply extend this to add further functionality if we create
 * ResidentViewFragment and CommunityViewFragment
 * Then both of them will have their own view models
 * And we will be implementing different interfaces for individual fragment views
 *
 * */
class PostsViewFragment : DaggerFragment(), PostAdapter.iPostAdapterActions {

    @Inject lateinit var viewModelProviderFactory: ViewModelProviderFactory
    @Inject lateinit var customSharedPreferences: CustomSharedPreferences

    private lateinit var viewModel : PostsViewModel
    private lateinit var binding : FragmentPostViewBinding

    val posts  = mutableListOf<DetailedPost>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(PostsViewModel::class.java)

        viewModel.getPosts().observe(this, Observer {
            posts.clear()
            posts.addAll(it)
            setupAdapter()
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPostViewBinding.inflate(inflater, container, false).apply {
            viewModel = viewModel
        }

        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        rcyPosts?.adapter?.let {
            it.notifyDataSetChanged()
        } ?: kotlin.run {
            rcyPosts?.adapter = PostAdapter(posts, customSharedPreferences, this)
        }
    }

    override fun onLikeClicked(post: DetailedPost, userLikedIt : Boolean) {
        //If user has liked a post
        customSharedPreferences.getUser()?.id?.let {
            if (userLikedIt) {
                viewModel.dislikePost(post.post.id, it).subscribe({},{})
            }
            else {
                viewModel.likePost(post.post.id, it).subscribe({},{})
            }
        } ?: kotlin.run {
            Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCommentClicked(post: DetailedPost) {
        //if user want to make a comment
        //lets start activity for the comment
        val intent = Intent(context, PostActivity::class.java).apply {
            putExtra(Post.TABLE_NAME, post.post.id)
        }

        startActivity(intent)
    }
}