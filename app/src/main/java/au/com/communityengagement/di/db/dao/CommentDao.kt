package au.com.communityengagement.di.db.dao

import androidx.room.Dao
import au.com.communityengagement.models.entitymodels.Comment

@Dao
interface CommentDao : BaseDao<Comment> {
}