package com.mezmeraiz.blank.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkResult<T> (

    @Expose
    @SerializedName("results")
    var results: MutableList<T>?,

    @Expose
    @SerializedName("info")
    var info: Info?

){
    data class Info (

        @Expose
        @SerializedName("page")
        var page: Int?

    )
}

