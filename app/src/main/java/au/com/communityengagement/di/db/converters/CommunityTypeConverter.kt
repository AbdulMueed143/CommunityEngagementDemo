package au.com.communityengagement.di.db.converters

import androidx.room.TypeConverter
import au.com.communityengagement.enums.CouncilType

class CommunityTypeConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun toStatus(type : Int) : CouncilType {

            if (type == CouncilType.TOWN.ordinal) {
                return CouncilType.TOWN
            }
            else if (type == CouncilType.SUBURB.ordinal) {
                return CouncilType.SUBURB
            }
            else {
                throw IllegalArgumentException("Unknown value for Community Type.")
            }
        }

        @TypeConverter
        @JvmStatic
        fun toInteger(type : CouncilType) : Int {
            return type.ordinal
        }
    }
}