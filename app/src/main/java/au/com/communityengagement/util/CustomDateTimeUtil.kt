package au.com.communityengagement.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class CustomDateTimeUtil {

    companion object {

        val dateTimePattern = "dd MMM yyyy hh:mm"
        val datePattern = "dd MMM yyyy"
        val timePattern = "hh:mm aa"

        val utcTimeZone = TimeZone.getTimeZone("UTC")
        val defaultTimezone = TimeZone.getDefault()

        @SuppressLint("SimpleDateFormat")
        val dateTimeFormat : SimpleDateFormat = SimpleDateFormat(dateTimePattern)

        @SuppressLint("SimpleDateFormat")
        val dateFormat : SimpleDateFormat = SimpleDateFormat(datePattern)

        @SuppressLint("SimpleDateFormat")
        val timeFormat : SimpleDateFormat = SimpleDateFormat(timePattern)

        fun getTodayInUTC() : Long {
            val calendar =  Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            return calendar.timeInMillis
        }

        fun getNowForCall() : Long {
            return Calendar.getInstance(TimeZone.getDefault()).timeInMillis
        }

        fun convertToUTCMillis(calendar: Calendar) : Long {
            val localCal = calendar.clone() as Calendar
            return localCal.timeInMillis
        }

        fun convertMillisToUTCMillis(currentMillis: Long) : Long {
            //just subtract the offset from the millis and you should hve the
            //utc time
            return currentMillis
        }

        fun getViewableDateTime(dateTime: Long) : String  {
            dateTimeFormat.timeZone = TimeZone.getDefault()
            return dateTimeFormat.format(Date(dateTime))
        }

        fun getViewableDate(dateTime: Long) : String  {
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(Date(dateTime))
        }

        fun getViewableTime(dateTime: Long) : String  {
            timeFormat.timeZone = TimeZone.getDefault()
            return timeFormat.format(Date(dateTime))
        }

        fun toDoubleDigit(singleDigit : Int) : String {
            if (singleDigit.toString().length == 1)
                return "0"+singleDigit

            return singleDigit.toString()
        }

        fun secondsToHour(seconds : Long) : Long {
            return (seconds % 86400 ) / 3600 ;
        }

        fun secondsToMinutes(seconds : Long) : Long {
            return ((seconds % 86400 ) % 3600 ) / 60
        }

        @SuppressLint("SimpleDateFormat")
        fun getDayNumber(date: Date) : String {
            return SimpleDateFormat("EE").format(date)
        }

        @SuppressLint("SimpleDateFormat")
        fun getDayOfWeekFromDate(date: Long) : String {
            return SimpleDateFormat("EE").format(date)
        }

    }
}