package com.mezmeraiz.blank.network.api

import com.mezmeraiz.blank.model.NetworkResult
import com.mezmeraiz.blank.model.User
import io.reactivex.Single
import retrofit2.http.*

interface UserApi {

    @GET("api")
    fun users(@Query("page") page : Int,
              @Query("results") results : Int,
              @Query("seed") seed : String): Single<NetworkResult<User>>

}