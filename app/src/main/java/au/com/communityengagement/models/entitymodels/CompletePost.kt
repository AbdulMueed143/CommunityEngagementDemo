package au.com.communityengagement.models.entitymodels

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

//This class is intended to get all the posts after left joining tables
@Parcelize
data class CompletePost(

        //Main table
    @Embedded
    val post: Post,

        //User object
    @Relation(
                parentColumn = Post.USER_ID,
                entityColumn = User.ID,
                entity = User::class
        )
        val user : User?,

//        //Community Object
//    @Relation(
//                parentColumn = User.CITY_COUNCIL_ID,
//                entityColumn = CityCouncil.ID,
//                entity = CityCouncil::class
//        )
//        val cityCouncil : CityCouncil?,

        //Comments
    @Relation(
            parentColumn = Post.ID,
            entityColumn = Comment.POST_ID,
            entity = Comment::class
        )
        val comments : MutableList<Comment>?,

        //Likes
    @Relation(
                parentColumn = Post.ID,
                entityColumn = Like.POST_ID,
                entity = Like::class
        )
        val likes : MutableList<Like>?

) : Parcelable {

    fun userIsInLikedList(userId: String) : Boolean {

        likes?.forEach {
            if (it.userId.equals(userId, true))
                return true
        }

        return false
    }

}