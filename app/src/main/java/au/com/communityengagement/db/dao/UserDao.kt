package au.com.communityengagement.db.dao

import androidx.room.Dao
import au.com.communityengagement.models.entitymodels.User

@Dao
interface UserDao : BaseDao<User> {

}