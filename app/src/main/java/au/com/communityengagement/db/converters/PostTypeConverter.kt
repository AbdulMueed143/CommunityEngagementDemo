package au.com.communityengagement.db.converters

import androidx.room.TypeConverter
import au.com.communityengagement.enums.PostType

class PostTypeConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun toStatus(type : Int) : PostType {

            if (type == PostType.POST.ordinal) {
                return PostType.POST
            }
            else if (type == PostType.ANNOUNCEMENT.ordinal) {
                return PostType.ANNOUNCEMENT
            }
            else {
                throw IllegalArgumentException("Unknown value for Post Type.")
            }
        }

        @TypeConverter
        @JvmStatic
        fun toInteger(type : PostType) : Int {
            return type.ordinal
        }
    }
}