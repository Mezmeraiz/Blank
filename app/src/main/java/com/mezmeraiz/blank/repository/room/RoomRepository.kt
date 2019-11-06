package com.mezmeraiz.blank.repository.room

import com.mezmeraiz.blank.db.UserDao
import com.mezmeraiz.blank.model.User
import io.reactivex.Single
import javax.inject.Inject

class RoomRepository @Inject constructor(private val userDao: UserDao) {

    fun getUsers(): Single<List<User>>{
        return userDao.getAllSavedUsers()
    }

}