package au.com.communityengagement.di.db.repositories

import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.util.DataGenerator

class PostsRepository {

    fun getPosts() : ArrayList<Post> {
        return DataGenerator.getPosts()
    }

}