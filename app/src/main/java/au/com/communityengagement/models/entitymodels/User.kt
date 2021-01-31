package au.com.communityengagement.models.entitymodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import au.com.communityengagement.enums.UserRole
import java.io.Serializable

@Entity(tableName = User.TABLE_NAME)
data class User(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id : String,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = USER_ROLE)
    val userType: UserRole,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: Long,

    @ColumnInfo(name = UPDATED_AT)
    val updatedAt: Long
) : Cloneable, Serializable  {

    companion object {
        const val TABLE_NAME = "user"
        const val ID = "id"
        const val NAME = "name"
        const val USER_ROLE = "user_role"
        const val CREATED_AT = "created_at"
        const val UPDATED_AT = "updated_at"
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun clone(): Any {
        return super.clone()
    }
}