package au.com.communityengagement.models.entitymodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import au.com.communityengagement.enums.CommunityType
import java.io.Serializable


/**
 * In perfect world we will identify town by using google API's
 * Google Places api can provide you place id for specific town..
 * if we assume communities as towns and use that we can create a common ground to identify how towns are distinguihed..
 * */
@Entity(tableName = Community.TABLE_NAME)
data class Community(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: String,

    @ColumnInfo(name = NAME)
    var name: String,

    @ColumnInfo(name = COMMUNITY_TYPE)
    val communityType : CommunityType,

    @ColumnInfo(name = CREATED_AT)
    var createdAt: Long,

    @ColumnInfo(name = UPDATED_AT)
    var updatedAt: Long,
) : Cloneable, Serializable {

    companion object {
        const val TABLE_NAME = "Community"
        const val ID = "id"
        const val NAME = "name"
        const val COMMUNITY_TYPE = "community_type"
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