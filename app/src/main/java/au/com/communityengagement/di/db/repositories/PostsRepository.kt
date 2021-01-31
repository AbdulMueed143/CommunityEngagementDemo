package au.com.communityengagement.di.db.repositories

import au.com.communityengagement.di.db.dao.PostDao
import au.com.communityengagement.models.entitymodels.CompletePost
import au.com.communityengagement.util.CustomSharedPreferences
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsRepository @Inject constructor(val postDao: PostDao,
                                          val customSharedPreferences: CustomSharedPreferences
) {
    fun getAllPosts() : Flowable<MutableList<CompletePost>> {
        return postDao.getAllPosts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getPost(postId: String) : Flowable<CompletePost> {
        return postDao.getPost(postId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}