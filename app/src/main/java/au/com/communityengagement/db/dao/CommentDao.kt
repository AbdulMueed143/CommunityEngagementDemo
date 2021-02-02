package au.com.communityengagement.db.dao

import androidx.room.Dao
import au.com.communityengagement.models.entitymodels.Comment

@Dao
interface CommentDao : BaseDao<Comment> {
}