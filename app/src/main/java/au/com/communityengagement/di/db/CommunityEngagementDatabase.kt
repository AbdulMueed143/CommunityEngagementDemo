package au.com.forteis.rhinocrm.db.entities

import android.content.Context
import android.util.Log
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
import au.com.communityengagement.util.CustomSharedPreferences
import au.com.communityengagement.util.DataGenerator
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.HashMap
import java.util.concurrent.Executors
import javax.inject.Inject

@Database(entities =
    [Post::class, Comment::class, User::class, Like::class, CityCouncil::class],
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
        private var completableList = ArrayList<Completable>()

//        val data  = HashMap<Long, Single<B>>()

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

                                    val instance =  getInstance(context)

                                    //Pre-fill Database

                                    completableList.add(instance.userDao().insertAll(DataGenerator.getUsers())
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread()))

                                    completableList.add(instance.cityCouncilDao().insertAll(DataGenerator.getCityCouncils())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()))

                                    completableList.add(instance.userDao().insertAll(DataGenerator.getCouncilStaff())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()))

                                    completableList.add(instance.postDao().insertAll(DataGenerator.getPosts())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()))

                                    completableList.add(instance.commentDao().insertAll(DataGenerator.getComments())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()))

                                    completableList.add(instance.likeDao().insertAll(DataGenerator.getLikes())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread()))

                                    //Execute them line by line each after other...
                                    //Because of foreign keys sequence is important...
                                    completableList.forEach {
                                        it.subscribe({},{})
                                    }

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
    abstract fun cityCouncilDao() : CityCouncilDao
    abstract fun likeDao() : LikeDao
    abstract fun postDao() : PostDao
    abstract fun userDao() : UserDao

}

