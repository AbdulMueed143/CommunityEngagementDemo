package au.com.communityengagement.models.entitymodels

import androidx.room.Embedded
import androidx.room.Relation

class CommentWithUser  {

    @Embedded var comment: Comment

    @Relation(
        parentColumn = Comment.USER_ID,
        entityColumn = User.ID,
        entity = User::class
    )
    var user : User?

    constructor(comment: Comment, user : User?) {
        this.comment = comment
        this.user = user
    }

}