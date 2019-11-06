package com.mezmeraiz.blank.network.common

import retrofit2.Retrofit

open class ServiceImpl<S>(private val clazz: Class<S>, val retrofit: Retrofit){

    fun getService(): S {
        return retrofit.create(clazz)
    }

}