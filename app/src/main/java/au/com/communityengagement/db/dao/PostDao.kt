package au.com.communityengagement.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.models.entitymodels.DetailedPost

@Dao
interface PostDao : BaseDao<Post> {

    @Transaction
    @Query("SELECT * FROM "+Post.TABLE_NAME+" WHERE "+Post.ID+" = :postId")
    fun getPost(postId: String) : LiveData<DetailedPost>

    @Transaction
    @Query("SELECT * FROM "+Post.TABLE_NAME + " ORDER BY "+Post.USER_ID+" ASC")
    fun getAllPosts() : LiveData<MutableList<DetailedPost>>

}