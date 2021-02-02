package au.com.communityengagement.db.repositories

import androidx.lifecycle.LiveData
import au.com.communityengagement.db.dao.CommentDao
import au.com.communityengagement.db.dao.LikeDao
import au.com.communityengagement.db.dao.PostDao
import au.com.communityengagement.models.entitymodels.Comment
import au.com.communityengagement.models.entitymodels.DetailedPost
import au.com.communityengagement.models.entitymodels.Like
import au.com.communityengagement.util.CustomSharedPreferences
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class PostsRepository @Inject constructor(val postDao: PostDao,
                                          val likeDao: LikeDao,
                                          val commentDao: CommentDao,
                                          val customSharedPreferences: CustomSharedPreferences
) {

    fun getAllPosts() : LiveData<MutableList<DetailedPost>> {
        return postDao.getAllPosts()
    }

    fun getPost(postId: String) : LiveData<DetailedPost> {
        return postDao.getPost(postId)
    }

    fun postComment(content: String, postId: String, userId: String) : Completable {
        return commentDao.insert(Comment(UUID.randomUUID().toString(), content, postId, userId))
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun likePost(postId: String, userId: String) : Completable {
        return likeDao.insert(Like(UUID.randomUUID().toString(), postId, userId)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun dislikePost(postId: String, userId: String) : Completable {
        return likeDao.deleteWhere(postId, userId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}