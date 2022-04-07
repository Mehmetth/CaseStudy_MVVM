package com.honeycomb.casestudy.domain.service.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostEntity(
    var title: String,
    var category: String,
    var image_url: String,
    var link: String
): Parcelable