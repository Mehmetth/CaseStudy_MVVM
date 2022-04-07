package com.honeycomb.casestudy.domain.service.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServiceDetailEntity(
    var id: Int?,
    var service_id: Int?,
    var name: String?,
    var long_name: String?,
    var image_url: String?,
    var pro_count: Int?,
    var average_rating: Float?,
    var completed_jobs_on_last_month: Int?
): Parcelable