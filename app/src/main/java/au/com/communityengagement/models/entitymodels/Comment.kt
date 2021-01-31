package au.com.communityengagement.models.entitymodels

import androidx.room.*
import au.com.communityengagement.util.CustomDateTimeUtil
import java.io.Serializable

@Entity(tableName = Comment.TABLE_NAME,

    indices = arrayOf(Index(value = [Comment.USER_ID, Comment.POST_ID])),

    foreignKeys = arrayOf(

        ForeignKey(entity = Post::class, parentColumns = [Post.ID], childColumns = [Comment.POST_ID]),

        ForeignKey(entity = User::class, parentColumns = [User.ID], childColumns = [Comment.USER_ID])
    )
)
data class Comment(
    @PrimaryKey
    @ColumnInfo(name = ID)
    var id: String,

    @ColumnInfo(name = CONTENT)
    var content: String,

    @ColumnInfo(name = POST_ID)
    var postId: String,

    @ColumnInfo(name = USER_ID)
    var userId: String,

    @ColumnInfo(name = CREATED_AT)
    var createdAt: Long = CustomDateTimeUtil.getTodayInUTC(),

    @ColumnInfo(name = UPDATED_AT)
    var updatedAt: Long = CustomDateTimeUtil.getTodayInUTC()

) : Cloneable, Serializable {

    companion object {
        const val TABLE_NAME = "Comment"
        const val ID = "id"
        const val CONTENT = "content"
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