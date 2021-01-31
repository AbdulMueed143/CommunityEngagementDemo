package au.com.communityengagement.models.entitymodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable

@Entity(tableName = Comment.TABLE_NAME)
data class Comment(
        @ColumnInfo(name = ID)
        var id: String,

        @ColumnInfo(name = ID)
        var content: String,

        @ColumnInfo(name = POST_ID)
        var postId: String,

        @ColumnInfo(name = USER_ID)
        var userId: String,

        @ColumnInfo(name = CREATED_AT)
        var createdAt: Long,

        @ColumnInfo(name = UPDATED_AT)
        var updatedAt: Long

) : Cloneable, Serializable {

    companion object {
        const val TABLE_NAME = "Comment"
        const val ID = "content"
        const val POST_ID = "postId"
        const val USER_ID = "user_id"
        const val CREATED_AT = "created_at"
        const val UPDATED_AT = "updated_at"
    }


    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun clone(): Any {
        return super.clone()
    }
}