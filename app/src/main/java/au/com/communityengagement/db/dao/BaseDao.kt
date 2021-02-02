package au.com.communityengagement.db.dao

import androidx.room.*
import io.reactivex.Completable
import org.jetbrains.annotations.NotNull


/**
 *
 * Base Dao has basic functions for all the daos..
 * you do not need to write insert, delete and update in every dao seperately
 *
 * */

@Dao
interface BaseDao<T> {

    @Insert
    @NotNull
    fun insert(item : T) : Completable

    @Insert
    @NotNull
    fun insertAll(itemList : List<T>) : Completable

    @Delete
    fun delete(item : T) : Completable

    @Delete
    fun deleteAll(item : MutableList<T>) : Completable

    @Update
    fun update(item : T) : Completable

    @Update
    fun updateAll(itemList : List<T>) : Completable

}