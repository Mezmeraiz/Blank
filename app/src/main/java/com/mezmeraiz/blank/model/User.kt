package com.mezmeraiz.blank.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(primaryKeys = ["name_first", "name_last", "saved"])
data class User  (

    var saved: Boolean = false,

    @Expose
    @SerializedName("name")
    @Embedded(prefix = "name_")
    var name: Name,

    @Expose
    @SerializedName("picture")
    @Embedded
    var picture: Picture

): Parcelable{
    @Parcelize
    data class Name (

        @Expose
        @SerializedName("first")
        var first: String,

        @Expose
        @SerializedName("last")
        var last: String

    ): Parcelable

    @Parcelize
    data class Picture (

        @Expose
        @SerializedName("large")
        var large: String?,

        @Expose
        @SerializedName("thumbnail")
        var thumbnail: String?

    ): Parcelable

}
