package com.honeycomb.casestudy.data.service.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostsResponse(
    @SerializedName("title")
    var title: String,
    @SerializedName("category")
    var category: String,
    @SerializedName("image_url")
    var image_url: String,
    @SerializedName("link")
    var link: String
): Parcelable