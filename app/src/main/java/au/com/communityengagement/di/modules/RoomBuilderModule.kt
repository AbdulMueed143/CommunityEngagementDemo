package au.com.communityengagement.di.modules

import android.app.Application
import au.com.communityengagement.db.CommunityEngagementDatabase
import au.com.communityengagement.db.dao.*
import au.com.communityengagement.db.repositories.PostsRepository
import au.com.communityengagement.util.CustomSharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomBuilderModule {

    //Provides database instance
    @Singleton
    @Provides
    fun providesCommunityEngagementDatabase(application: Application) : CommunityEngagementDatabase {
        return CommunityEngagementDatabase.getInstance(application.applicationContext)
    }

    //Custom shared preference, we will use for keeping track of current user
    @Singleton
    @Provides
    fun providesCustomSharedPreference(application: Application) : CustomSharedPreferences {
        return CustomSharedPreferences.getInstance(application.applicationContext)
    }

    //Dao Classes
    @Singleton
    @Provides
    fun provideCommentDao(communityEngagementDatabase : CommunityEngagementDatabase) : CommentDao {
        return communityEngagementDatabase.commentDao()
    }

    @Singleton
    @Provides
    fun provideCityCouncilDao(communityEngagementDatabase : CommunityEngagementDatabase) : CityCouncilDao {
        return communityEngagementDatabase.cityCouncilDao()
    }

    @Singleton
    @Provides
    fun provideLikeDao(communityEngagementDatabase : CommunityEngagementDatabase) : LikeDao {
        return communityEngagementDatabase.likeDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(communityEngagementDatabase : CommunityEngagementDatabase) : UserDao {
        return communityEngagementDatabase.userDao()
    }

    @Singleton
    @Provides
    fun providePostDao(communityEngagementDatabase : CommunityEngagementDatabase) : PostDao {
        return communityEngagementDatabase.postDao()
    }

    //Repositories
    @Singleton
    @Provides
    fun providePostRepository(postDao: PostDao, likeDao: LikeDao, commentDao: CommentDao,customSharedPreferences: CustomSharedPreferences) : PostsRepository {
        return PostsRepository(postDao, likeDao , commentDao, customSharedPreferences)
    }

}