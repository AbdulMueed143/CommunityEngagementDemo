package au.com.communityengagement.models.entitymodels

import androidx.room.*
import au.com.communityengagement.enums.PostType
import java.io.Serializable

//We can also convert same class into json parceable by adding searlise and other stuff
@Entity(tableName = Post.TABLE_NAME,

        indices = arrayOf(Index(value = [Post.USER_ID, Post.COMMUNITY_ID])),

        foreignKeys = arrayOf(

                ForeignKey(entity = User::class, parentColumns = [User.ID], childColumns = [Post.USER_ID]),

                ForeignKey(entity = Community::class, parentColumns = [Community.ID], childColumns = [Post.COMMUNITY_ID])
        )
)
data class Post(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id : String,

    @ColumnInfo(name = CONTENT)
    val content: String,

    @ColumnInfo(name = USER_ID)
    val userId : String,

    @ColumnInfo(name = COMMUNITY_ID)
    val communityId: String,

    @ColumnInfo(name = POST_TYPE)
    val postType: PostType,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: Long,

    @ColumnInfo(name = UPDATED_AT)
    val updatedAt: Long,
) : Cloneable, Serializable {
    companion object {
        const val TABLE_NAME = "post"
        const val ID = "id"
        const val CONTENT = "content"
        const val USER_ID = "user_id"
        const val COMMUNITY_ID = "community_id"
        const val POST_TYPE = "post_type"
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