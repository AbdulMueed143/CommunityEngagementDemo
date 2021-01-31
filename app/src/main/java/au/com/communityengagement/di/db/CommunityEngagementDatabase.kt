package au.com.forteis.rhinocrm.db.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import au.com.communityengagement.db.converters.DateTimeConverter
import au.com.communityengagement.db.converters.UserRoleConverter
import au.com.communityengagement.di.db.converters.CommunityTypeConverter
import au.com.communityengagement.di.db.converters.PostTypeConverter
import au.com.communityengagement.di.db.dao.*
import au.com.communityengagement.models.entitymodels.*
import java.util.concurrent.Executors

@Database(entities =
    [Post::class, Comment::class, User::class, Like::class, Community::class],
    version = CommunityEngagementDatabase.VERSION_NUMBER, exportSchema = false
)
//These are enum converters and other data type converters like datetime
@TypeConverters(UserRoleConverter::class,
    DateTimeConverter::class,
    PostTypeConverter::class,
    CommunityTypeConverter::class
)
abstract class CommunityEngagementDatabase : RoomDatabase() {

    companion object {

        val name: String = "CommunityEngagementDatabase"
        const val VERSION_NUMBER = 1

        private var instance: CommunityEngagementDatabase? = null

        fun getInstance(context: Context) : CommunityEngagementDatabase {

            if(instance == null) {

                synchronized(CommunityEngagementDatabase::class) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CommunityEngagementDatabase::class.java, name)
                        .fallbackToDestructiveMigration()
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                Executors.newSingleThreadExecutor().execute {
                                    //Well we will initialise our database with default values of posts over here..

                                    //Add Users

                                    //Add Communities

                                    //Add Posts For Users

                                    //Add Comments and Likes

                                    //Add Posts For Communities

                                    //Add Comments and Likes
                                }
                            }
                        })
                        .build()
                }
            }

            return instance!!
        }
    }

    //We need to define each dao class over here...
    abstract fun commentDao() : CommentDao
    abstract fun communityDao() : CommunityDao
    abstract fun likeDao() : LikeDao
    abstract fun postDao() : PostDao
    abstract fun userDao() : UserDao

}

