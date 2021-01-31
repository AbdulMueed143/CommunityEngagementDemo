package au.com.communityengagement.models.viewmodels

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.di.db.repositories.PostsRepository
import au.com.communityengagement.models.entitymodels.CompletePost
import io.reactivex.Flowable
import javax.inject.Inject

class PostViewModel @Inject constructor(val postsRepository: PostsRepository) : ViewModel(), Observable {

    //These posts are being monitored .. on change it will try to update the recyclerview
    fun getPosts() : Flowable<MutableList<CompletePost>> {
        return postsRepository.getAllPosts()
    }

    fun getPost(postId : String) : Flowable<CompletePost> {
        return postsRepository.getPost(postId)
    }

    //We can use following to change state of things on the screen...
    val propertyChangeRegistry : PropertyChangeRegistry = PropertyChangeRegistry()

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

}