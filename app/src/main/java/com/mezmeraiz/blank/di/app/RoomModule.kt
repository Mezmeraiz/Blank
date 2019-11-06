package com.mezmeraiz.blank.di.app

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import com.mezmeraiz.blank.db.AppDatabase
import com.mezmeraiz.blank.db.UserDao


@Module
class RoomModule() {

    @Singleton
    @Provides
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java,
            "database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.users()
    }

}