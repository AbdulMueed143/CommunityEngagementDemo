package au.com.communityengagement.models.viewmodels

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.di.db.repositories.PostsRepository
import javax.inject.Inject

class PostViewModel @Inject constructor(private val postsRepository: PostsRepository) : ViewModel(), Observable {

    //These posts are being monitored .. on change it will try to update the recyclerview
    val posts : MutableLiveData<ArrayList<Post>> = MutableLiveData()

    //Initialize it
    init {
        if (posts.value == null)
            posts.value = ArrayList()
    }

    fun load() {
        posts.value = postsRepository.getPosts()
    }

    val propertyChangeRegistry : PropertyChangeRegistry = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

}