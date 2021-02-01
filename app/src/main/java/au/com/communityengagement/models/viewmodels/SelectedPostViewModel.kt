package au.com.communityengagement.models.viewmodels

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import au.com.communityengagement.BR
import au.com.communityengagement.R
import au.com.communityengagement.di.db.repositories.PostsRepository
import au.com.communityengagement.models.entitymodels.DetailedPost
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.models.entitymodels.User
import au.com.communityengagement.util.CustomSharedPreferences
import io.reactivex.Flowable
import javax.inject.Inject

class SelectedPostViewModel @Inject constructor(val postsRepository: PostsRepository) : ViewModel(), Observable {

    //These posts are being monitored .. on change it will try to update the recyclerview

    //We can use following to change state of things on the screen...
    @Inject lateinit var customSharedPreferences: CustomSharedPreferences
    val propertyChangeRegistry : PropertyChangeRegistry = PropertyChangeRegistry()

    //This is so that we can update the ui when we get the data
    @Bindable
    var user : User? = null
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.user)
        }

    @Bindable
    var post : Post? = null
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.post)
        }

    @Bindable
    var isLiked : Boolean = false
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.isLiked)
            //Because when like is changed we want to reflect that in view
            propertyChangeRegistry.notifyChange(this, BR.resourceId)
        }
        get() {
            return field
        }

    @Bindable
    var resourceId : Int = R.drawable.ic_like
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.resourceId)
        }
        get() {
            if (isLiked)
                return R.drawable.ic_liked
            else
                return R.drawable.ic_like
        }

    @Bindable
    var comment : String = ""
        set(value) {
            field = value
            propertyChangeRegistry.notifyChange(this, BR.comment)
        }
        get() {
            return field
        }


    @SuppressLint("CheckResult")
    fun onCommentSend() {

        //Validate
        val userId = customSharedPreferences.getUser()?.id ?: ""
        val postId = post?.id ?: ""

        postsRepository.postComment(comment, postId, userId).subscribe({
            //success
            comment = ""
        },{
            //failure
            Log.e("Comment", "Comment")
        })
    }

    fun onLikeButtonClick() {
        val userId = customSharedPreferences.getUser()?.id ?: ""
        val postId = post?.id ?: ""

        if (isLiked) {
            postsRepository.dislikePost(postId, userId).subscribe()
        }
        else {
            postsRepository.likePost(postId, userId).subscribe()
        }

    }

    fun getPost(postId: String) : LiveData<DetailedPost>  {
        return postsRepository.getPost(postId)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

}