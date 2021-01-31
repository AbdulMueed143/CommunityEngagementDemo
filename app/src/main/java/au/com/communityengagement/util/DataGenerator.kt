package au.com.communityengagement.util

import au.com.communityengagement.enums.CouncilType
import au.com.communityengagement.enums.PostType
import au.com.communityengagement.enums.UserRole
import au.com.communityengagement.models.entitymodels.*
import java.util.*
import kotlin.collections.ArrayList

object DataGenerator {

    //We have to declare id's at runtime but we also want to use
    //Same id's when we add post, comments and likes
    //and that is why we need to initialise them here

    //Lazy initializes val that is kind of constant but at runtime
    private val USER_1 : String by  lazy { UUID.randomUUID().toString() }
    private val USER_2 : String by  lazy { UUID.randomUUID().toString() }
    private val USER_3 : String by  lazy { UUID.randomUUID().toString() }

    private val USER_4 : String by  lazy { UUID.randomUUID().toString() }
    private val USER_5 : String by  lazy { UUID.randomUUID().toString() }
    private val USER_6 : String by  lazy { UUID.randomUUID().toString() }

    private val CITY_COUNCIL_1 : String by  lazy { UUID.randomUUID().toString() }
    private val CITY_COUNCIL_2 : String by lazy { UUID.randomUUID().toString() }
    private val CITY_COUNCIL_3 : String by  lazy { UUID.randomUUID().toString() }

    private val POST_1 : String by  lazy { UUID.randomUUID().toString() }
    private val POST_2 : String by  lazy { UUID.randomUUID().toString() }
    private val POST_3 : String by  lazy { UUID.randomUUID().toString() }
    private val POST_4 : String by  lazy { UUID.randomUUID().toString() }
    private val POST_5 : String by  lazy { UUID.randomUUID().toString() }
    private val POST_6 : String by  lazy { UUID.randomUUID().toString() }
    private val POST_7 : String by  lazy { UUID.randomUUID().toString() }
    private val POST_8 : String by  lazy { UUID.randomUUID().toString() }
    private val POST_9 : String by  lazy { UUID.randomUUID().toString() }


    //Users empty list
    val mutableListOfUsers = mutableListOf<User>()
    //CityCouncil empty list
    val mutableListOfCitycouncil = mutableListOf<CityCouncil>()
    val mutableListOfCitycouncilStaff = mutableListOf<User>()

    //Posts
    val mutableListOfPosts = mutableListOf<Post>()

    //Likes and comments for the posts
    val mutableListOfLikes  = mutableListOf<Like>()
    val mutableListOfComments = mutableListOf<Comment>()

    fun getUsers() : MutableList<User> {

        mutableListOfUsers.add(User(USER_1,
            "Abdul Mueed",
            UserRole.RESIDENT, null ))

        mutableListOfUsers.add(User(USER_2,
            "John Leanon",
            UserRole.RESIDENT, null ))

        mutableListOfUsers.add(User(USER_3,
            "Ricky Jervais",
            UserRole.RESIDENT, null ))

        return mutableListOfUsers
    }

    fun getCurrentUser() : User {
        return User(USER_1,
            "Abdul Mueed",
            UserRole.RESIDENT, null )
    }

    fun getCityCouncils() : MutableList<CityCouncil> {

        mutableListOfCitycouncil.add(CityCouncil(CITY_COUNCIL_1, "Olympic Park", CouncilType.SUBURB))

        mutableListOfCitycouncil.add(CityCouncil(CITY_COUNCIL_2, "Sydney", CouncilType.TOWN))

        mutableListOfCitycouncil.add(CityCouncil(CITY_COUNCIL_3, "Wollongong", CouncilType.TOWN))

        return mutableListOfCitycouncil
    }

    fun getCouncilStaff() : MutableList<User> {

        mutableListOfCitycouncilStaff.add(User(
            USER_4, "Olympic Park",
            UserRole.CITY_COUNCIL_STAFF,
            mutableListOfCitycouncil.get(0).id))

        mutableListOfCitycouncilStaff.add(User(
            USER_5, "Sydney",
            UserRole.CITY_COUNCIL_STAFF,
            mutableListOfCitycouncil.get(1).id))

        mutableListOfCitycouncilStaff.add(User(
            USER_6, "Wollongong",
            UserRole.CITY_COUNCIL_STAFF,
            mutableListOfCitycouncil.get(2).id))

        return mutableListOfCitycouncilStaff
    }

    fun getPosts() : MutableList<Post> {


        //We will add posts by users
        mutableListOfPosts.add(Post(POST_1, "I am gonna buy AMC after I missed the jump in GameStop.", USER_1, PostType.POST))
        mutableListOfPosts.add(Post(POST_2, "Who wants to join me on tracking?.", USER_2, PostType.POST))
        mutableListOfPosts.add(Post(POST_3, "This is great that Australia has controlled Covid19 this well.", USER_3, PostType.POST))
        mutableListOfPosts.add(Post(POST_4, "I have no idea when will lucid motors announce their merger with CCIV, been waiting from too long now.", USER_1, PostType.POST))

        //We will  add posts by city councils
        mutableListOfPosts.add(Post(POST_5, "The vaccine is about to be available, we hope everyone understands and gets vaccinated ASAP.", USER_4, PostType.POST))
        mutableListOfPosts.add(Post(POST_6, "The high risk people will be vaccinated first.", USER_5, PostType.POST))

        //We will add announcements by city councils staff
        mutableListOfPosts.add(Post(POST_7, "We will be adding further covid 19 restrictions.", USER_6, PostType.ANNOUNCEMENT))
        mutableListOfPosts.add(Post(POST_8, "Masks are mandatory from now onwards.", USER_4, PostType.ANNOUNCEMENT))
        mutableListOfPosts.add(Post(POST_9, "We will restrict gathering overs 5 people.", USER_5, PostType.ANNOUNCEMENT))

        return mutableListOfPosts
    }

    fun getLikes() : MutableList<Like> {

        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_1, USER_1))
        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_1, USER_3))
        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_1, USER_4))

        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_2, USER_6))
        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_2, USER_5))

        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_3, USER_6))
        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_3, USER_1))

        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_4, USER_1))

        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_6, USER_2))

        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_7, USER_3))
        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_7, USER_4))
        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_7, USER_5))
        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_7, USER_6))

        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_9, USER_2))
        mutableListOfLikes.add(Like(UUID.randomUUID().toString(), POST_9, USER_3))

        return mutableListOfLikes
    }

    fun getComments() : MutableList<Comment> {

        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "Ohh, I will try to search about that do you have link to reddit?", POST_1, USER_2))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "I would love to, where are you going?", POST_2, USER_3))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "How does blue mountains sound?", POST_2, USER_2))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "Sounds perfect to me.", POST_2, USER_3))

        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_3, USER_2))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_4, USER_5))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_5, USER_4))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_6, USER_5))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_7, USER_1))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_8, USER_3))

        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_2, USER_3))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_3, USER_2))
        mutableListOfComments.add(Comment(UUID.randomUUID().toString(), "This is a comment.", POST_5, USER_1))

        return mutableListOfComments
    }

}