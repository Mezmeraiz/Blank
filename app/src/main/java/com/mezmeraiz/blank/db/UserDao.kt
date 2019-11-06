package com.mezmeraiz.blank.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mezmeraiz.blank.model.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users : List<User>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user : User): Long

    @Query("SELECT * FROM User WHERE saved = 0")
    fun getAllCachedUsers() : DataSource.Factory<Int, User>

    @Query("SELECT * FROM User WHERE saved = 1")
    fun getAllSavedUsers() : Single<List<User>>

    @Query("DELETE FROM User WHERE saved = 0")
    fun delete()

}