package au.com.communityengagement.util

import android.content.Context
import au.com.communityengagement.R
import au.com.communityengagement.models.entitymodels.User
import com.google.gson.Gson
import javax.inject.Singleton


@Singleton
class CustomSharedPreferences(private val context: Context) {

    companion object {

        private var instance: CustomSharedPreferences? = null

        fun getInstance(context: Context) : CustomSharedPreferences {
            if(instance == null) {
                synchronized(CustomSharedPreferences::class) {
                    instance = CustomSharedPreferences(context)
                }
            }

            return instance!!
        }
    }

    fun saveUser(user: User) {
        var sharedPreference = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        var userJson = Gson().toJson(user)
        editor.putString(User.TABLE_NAME, userJson)
        editor.apply()
    }

//    fun saveState(key: String, value: Boolean) {
//        var sharedPreferences = context.getSharedPreferences(context?.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
//        var editor = sharedPreferences.edit()
//        editor.putString(key, value.toString()).apply()
//    }

    fun getUser() : User? {
        var sharedPreference = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        var userString = sharedPreference.getString(User.TABLE_NAME, "")
        var user = Gson().fromJson(userString, User::class.java)
        return user
    }

//    fun getState(key: String) : Boolean? {
//        var sharedPreference = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
//        return sharedPreference.getString(key, "false")?.toBoolean()
//    }

    fun deleteUser() {
        var sharedPreference = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        sharedPreference.edit().remove(User.TABLE_NAME).apply()
    }

}