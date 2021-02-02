package au.com.communityengagement.models.entitymodels

import androidx.room.Embedded
import androidx.room.Relation

class DetailedPost(

    @Embedded
    var post: Post,

    @Relation(
        parentColumn = Post.USER_ID,
        entityColumn = User.ID,
        entity = User::class
    )
    var user : UserWithCityCouncil?,

    @Relation(
        parentColumn = Post.ID,
        entityColumn = Comment.POST_ID,
        entity = Comment::class
    )
    var comments : MutableList<CommentWithUser>?,

    @Relation(
        parentColumn = Post.ID,
        entityColumn = Like.POST_ID,
        entity = Like::class
    )
    var likes : MutableList<Like>?) {

    fun userIsInLikedList(userId: String) : Boolean {

        likes?.forEach {
            if (it.userId.equals(userId, true))
                return true
        }

        return false
    }

    fun userIsInLikedList(user: User?) : Boolean {
        user?.let{
                likes?.forEach { like-> kotlin.run {
                    if (like.userId.equals(it.id, true))
                        return true
                }
            }
        }
        return false
    }

}