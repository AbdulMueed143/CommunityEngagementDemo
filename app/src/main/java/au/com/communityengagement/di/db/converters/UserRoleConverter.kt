package au.com.communityengagement.db.converters

import androidx.room.TypeConverter
import au.com.communityengagement.enums.UserRole

class UserRoleConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun toStatus(role : Int) : UserRole {

            if (role == UserRole.COMMUNITY.ordinal) {
                return UserRole.COMMUNITY
            }
            else if (role == UserRole.RESIDENT.ordinal) {
                return UserRole.RESIDENT
            }
            else {
                throw IllegalArgumentException("Unknown value for User Role.") as Throwable
            }
        }

        @TypeConverter
        @JvmStatic
        fun toInteger(role : UserRole) : Int {
            return role.ordinal
        }
    }
}