package au.com.communityengagement.models.entitymodels

import androidx.room.*
import au.com.communityengagement.util.CustomDateTimeUtil
import java.io.Serializable

@Entity(tableName = Like.TABLE_NAME,

        indices = arrayOf(Index(value = [Like.USER_ID, Like.POST_ID])),

        foreignKeys = arrayOf(

                ForeignKey(entity = Post::class, parentColumns = [Post.ID], childColumns = [Like.POST_ID]),

                ForeignKey(entity = User::class, parentColumns = [User.ID], childColumns = [Like.USER_ID])
        )

)
data class Like(
        @PrimaryKey
        @ColumnInfo(name = ID)
        var id: String,

        @ColumnInfo(name = POST_ID)
        var postId : String,

        @ColumnInfo(name = USER_ID)
        var userId: String,

        @ColumnInfo(name = CREATED_AT)
        var createdAt: Long = CustomDateTimeUtil.getTodayInUTC(),

        @ColumnInfo(name = UPDATED_AT)
        var updatedAt: Long = CustomDateTimeUtil.getTodayInUTC()
) : Cloneable, Serializable {

        companion object {
                const val TABLE_NAME = "like"
                const val ID = "id"
                const val POST_ID = "post_id"
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