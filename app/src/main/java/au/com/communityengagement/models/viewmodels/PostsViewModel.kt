package au.com.communityengagement.models.viewmodels

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import au.com.communityengagement.di.db.repositories.PostsRepository
import au.com.communityengagement.models.entitymodels.DetailedPost
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class PostsViewModel @Inject constructor(val postsRepository: PostsRepository) : ViewModel(), Observable {

    fun getPosts() : LiveData<MutableList<DetailedPost>> {
        return postsRepository.getAllPosts()
    }

    fun likePost(postId: String, userId: String) : Completable {
        return postsRepository.likePost(postId, userId)
    }

    fun dislikePost(postId: String, userId: String) : Completable {
        return postsRepository.dislikePost(postId, userId)
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