package au.com.communityengagement.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import au.com.communityengagement.R
import au.com.communityengagement.adapters.PostAdapter
import au.com.communityengagement.databinding.FragmentPostViewBinding
import au.com.communityengagement.di.ViewModelProviderFactory
import au.com.communityengagement.models.entitymodels.CompletePost
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.models.viewmodels.PostViewModel
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
class PostsViewFragment : DaggerFragment() {

    @Inject lateinit var viewModelProviderFactory: ViewModelProviderFactory
    @Inject lateinit var customSharedPreferences: CustomSharedPreferences

    private lateinit var viewModel : PostViewModel
    private lateinit var binding : FragmentPostViewBinding

    val posts  = mutableListOf<CompletePost>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(PostViewModel::class.java)
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

        //Now we can observe changes in view model here
        viewModel.getPosts().subscribe( {
            posts.clear()
            posts.addAll(it)
        }, {
            //This is where the error comes
        })

        setupAdapter()
    }

    private fun setupAdapter() {
        rcyPosts.adapter?.let {
            it.notifyDataSetChanged()
        } ?: kotlin.run {
            rcyPosts?.adapter = PostAdapter(posts, customSharedPreferences)
        }
    }

}