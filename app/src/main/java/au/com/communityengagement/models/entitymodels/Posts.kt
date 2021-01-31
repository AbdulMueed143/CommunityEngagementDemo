package au.com.communityengagement.models.entitymodels

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.android.parcel.Parcelize

//This class is intended to get all the posts after left joining tables
@Parcelize
data class Posts(

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

        //Community Object
        @Relation(
                parentColumn = Post.COMMUNITY_ID,
                entityColumn = Community.ID,
                entity = Community::class
        )
        val community : Community?,

        //Comments
        @Relation(
            parentColumn = Post.ID,
            entityColumn = Comment.ID,
            entity = Comment::class
        )
        val comments : MutableList<Comment>?,

        //Likes
        @Relation(
                parentColumn = Post.ID,
                entityColumn = Like.ID,
                entity = Like::class
        )
        val likes : MutableList<Like>?

) : Parcelable {

}