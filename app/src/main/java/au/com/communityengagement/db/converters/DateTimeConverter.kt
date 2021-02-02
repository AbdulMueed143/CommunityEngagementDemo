package au.com.communityengagement.db.converters

import androidx.room.TypeConverter
import au.com.communityengagement.util.CustomDateTimeUtil
import java.util.*

class DateTimeConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromTimestamp(value: Long) : Date? {
            CustomDateTimeUtil.dateTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
            val calendar =  Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.timeInMillis = value
            return calendar.time
        }

        @TypeConverter
        @JvmStatic
        fun toTimestamp(date: Date) : Long {
            val calendar =  Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            calendar.time = date
            return calendar.timeInMillis
        }
    }

}