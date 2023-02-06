package com.android.rxJavaDagger.model

 import com.google.gson.annotations.SerializedName

data class VenueFeedData(
    @SerializedName("venues") val venues: ArrayList<VenueShowFeedItem>
)