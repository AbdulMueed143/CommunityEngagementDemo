package au.com.communityengagement.di.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import au.com.communityengagement.models.entitymodels.Like
import io.reactivex.Completable

@Dao
interface LikeDao : BaseDao<Like> {
    @Query( "DELETE FROM "+Like.TABLE_NAME+" WHERE "+Like.POST_ID+" =:postId AND "+Like.USER_ID+" =:userId ")
    fun deleteWhere(postId: String, userId: String) : Completable
}