package au.com.communityengagement.util

import au.com.communityengagement.enums.PostType
import au.com.communityengagement.models.entitymodels.Comment
import au.com.communityengagement.models.entitymodels.Community
import au.com.communityengagement.models.entitymodels.Like
import au.com.communityengagement.models.entitymodels.Post
import java.util.*
import kotlin.collections.ArrayList

object DataGenerator {

    private const val USER_1 = "XXEQDAEQ-2341-RRWPWE"
    private const val USER_2 = "XXEQDAEQ-2341-RYRWGS"
    private const val COMMUNITY_1 = "XXEQDAEQ-2341-LPERW"
    private const val COMMUNITY_2 = "XXEQDAEQ-2341-PQRWL"

    val communities : ArrayList<Community> = ArrayList()
    val communityPosts : ArrayList<Post> = ArrayList()

    val likes : ArrayList<Like> = ArrayList()
    val comments : ArrayList<Comment> = ArrayList()

    fun getPosts() : ArrayList<Post> {

        val post1ByUser1  = Post(UUID.randomUUID().toString(),
        "There is test post by user 1.",
        USER_1,
        COMMUNITY_1, PostType.POST, comments, likes)

        communityPosts.add(post1ByUser1)

        val post2ByUser1  = Post(UUID.randomUUID().toString(),
                "There is test post by user 1.",
                USER_1,
                COMMUNITY_2, PostType.POST, comments, likes)

        communityPosts.add(post2ByUser1)


        val post1ByUser2  = Post(UUID.randomUUID().toString(),
                "There is test post by user 1.",
                USER_2,
                COMMUNITY_1, PostType.POST, comments, likes)

        communityPosts.add(post1ByUser2)

        val post2ByUser2  = Post(UUID.randomUUID().toString(),
                "There is test post by user 1.",
                USER_2,
                COMMUNITY_1, PostType.ANNOUNCEMENT, comments, likes)

        communityPosts.add(post2ByUser2)

        return communityPosts
    }

}