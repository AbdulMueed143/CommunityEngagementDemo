package au.com.communityengagement.di.db.converters

import androidx.room.TypeConverter
import au.com.communityengagement.enums.CommunityType

class CommunityTypeConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun toStatus(type : Int) : CommunityType {

            if (type == CommunityType.TOWN.ordinal) {
                return CommunityType.TOWN
            }
            else if (type == CommunityType.GROUP.ordinal) {
                return CommunityType.GROUP
            }
            else if (type == CommunityType.SUBURB.ordinal) {
                return CommunityType.SUBURB
            }
            else {
                throw IllegalArgumentException("Unknown value for Community Type.") as Throwable
            }
        }

        @TypeConverter
        @JvmStatic
        fun toInteger(type : CommunityType) : Int {
            return type.ordinal
        }
    }
}