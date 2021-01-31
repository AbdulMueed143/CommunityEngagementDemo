package au.com.communityengagement.di.db.dao

import androidx.room.Dao
import androidx.room.Query
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.models.entitymodels.CompletePost
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PostDao : BaseDao<Post> {

    @Query("SELECT * FROM "+Post.TABLE_NAME+" WHERE "+Post.ID+" = :postId")
    fun getPost(postId: String) : Flowable<CompletePost>

    @Query("SELECT * FROM "+Post.TABLE_NAME)
    fun getAllPosts() : Flowable<MutableList<CompletePost>>

}