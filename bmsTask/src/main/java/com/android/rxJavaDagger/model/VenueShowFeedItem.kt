package com.android.rxJavaDagger.model

import com.google.gson.annotations.SerializedName

data class VenueShowFeedItem(
    @SerializedName("name") val title: String,
    @SerializedName("showDate") val showDate: String,
    @SerializedName("showtimes") val showTimeList: ArrayList<MovieShowTimeListData> = arrayListOf()
)

data class MovieShowTimeListData(
    @SerializedName("showTime") val showTime: String? = null,
    @SerializedName("showDateCode") val showDateCode: String? = null,
)


