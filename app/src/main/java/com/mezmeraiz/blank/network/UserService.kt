package com.mezmeraiz.blank.network

import com.mezmeraiz.blank.model.NetworkResult
import com.mezmeraiz.blank.model.User
import com.mezmeraiz.blank.network.api.UserApi
import com.mezmeraiz.blank.network.common.ServiceImpl
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(retrofit: Retrofit) : ServiceImpl<UserApi>(UserApi::class.java, retrofit) {

    fun users(page : Int, results: Int): Single<NetworkResult<User>> {
        return getService().users(page, results, "abc")
    }

}