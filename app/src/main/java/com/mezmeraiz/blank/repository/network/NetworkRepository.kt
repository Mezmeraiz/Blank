package com.mezmeraiz.blank.repository.network

import com.mezmeraiz.blank.model.NetworkResult
import com.mezmeraiz.blank.model.User
import com.mezmeraiz.blank.network.UserService
import io.reactivex.Single
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val userService: UserService) {

    fun getUsers(): Single<NetworkResult<User>>{
        return userService.users(1, 50)
    }

}