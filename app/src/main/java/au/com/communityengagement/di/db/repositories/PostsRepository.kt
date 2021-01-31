package au.com.communityengagement.di.db.repositories

import au.com.communityengagement.di.db.dao.PostDao
import au.com.communityengagement.models.entitymodels.Post
import au.com.communityengagement.util.DataGenerator
import javax.inject.Inject

class PostsRepository @Inject constructor(val postDao: PostDao) {

    fun getAllPosts() {

    }
}