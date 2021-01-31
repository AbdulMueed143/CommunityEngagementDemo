package au.com.communityengagement.di.db.dao

import androidx.room.Dao
import au.com.communityengagement.models.entitymodels.User

@Dao
interface UserDao : BaseDao<User> {

}