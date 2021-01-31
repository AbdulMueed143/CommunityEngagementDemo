package au.com.communityengagement.models.entitymodels

import androidx.room.*
import au.com.communityengagement.enums.UserRole
import au.com.communityengagement.util.CustomDateTimeUtil
import java.io.Serializable

@Entity(
    tableName = User.TABLE_NAME,

    indices = arrayOf(Index(value = [User.CITY_COUNCIL_ID])),

    foreignKeys = arrayOf(
        ForeignKey(entity = CityCouncil::class, parentColumns = [CityCouncil.ID], childColumns = [User.CITY_COUNCIL_ID]),
    )
)
data class User(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id : String,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = USER_ROLE)
    val userRole: UserRole,

    //You can have it as null, you will have this object when user is of council staff type
    @ColumnInfo(name = CITY_COUNCIL_ID)
    val cityCouncil: String?,

    @ColumnInfo(name = CREATED_AT)
    val createdAt: Long = CustomDateTimeUtil.getTodayInUTC(),

    @ColumnInfo(name = UPDATED_AT)
    val updatedAt: Long = CustomDateTimeUtil.getTodayInUTC()
) : Cloneable, Serializable  {

    companion object {
        const val TABLE_NAME = "user"
        const val ID = "id"
        const val NAME = "name"
        const val USER_ROLE = "user_role"
        const val CITY_COUNCIL_ID = "city_council_id"
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