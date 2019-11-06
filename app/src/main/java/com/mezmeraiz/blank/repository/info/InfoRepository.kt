package com.mezmeraiz.blank.repository.info

import com.mezmeraiz.blank.db.UserDao
import com.mezmeraiz.blank.model.User
import io.reactivex.Single
import javax.inject.Inject

class InfoRepository @Inject constructor(private val userDao: UserDao) {

    fun insertUser(user: User): Single<Long>{
        return Single.fromCallable {
            userDao.insert(user)
        }
    }

}