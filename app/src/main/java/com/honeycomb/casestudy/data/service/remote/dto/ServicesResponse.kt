package com.honeycomb.casestudy.data.service.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.honeycomb.casestudy.data.service.remote.dto.PostsResponse
import com.honeycomb.casestudy.data.service.remote.dto.ServiceResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServicesResponse(
    @SerializedName("all_services")
    var serviceResponse: List<ServiceResponse>,
    @SerializedName("popular")
    var popular: List<ServiceResponse>,
    @SerializedName("posts")
    var postsResponse: List<PostsResponse>,
): Parcelable
