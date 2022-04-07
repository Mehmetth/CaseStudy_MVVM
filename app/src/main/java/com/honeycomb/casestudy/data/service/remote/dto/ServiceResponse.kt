package com.honeycomb.casestudy.data.service.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServiceResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("service_id")
    var service_id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("long_name")
    var long_name: String,
    @SerializedName("image_url")
    var image_url: String,
    @SerializedName("pro_count")
    var pro_count: Int
): Parcelable