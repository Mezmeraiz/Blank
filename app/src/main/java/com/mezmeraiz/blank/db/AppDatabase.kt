package com.mezmeraiz.blank.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mezmeraiz.blank.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun users(): UserDao

}