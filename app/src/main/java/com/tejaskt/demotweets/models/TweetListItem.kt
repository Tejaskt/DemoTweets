package com.tejaskt.demotweets.models

import com.google.gson.annotations.SerializedName

data class TweetListItem(
    @SerializedName("category") val category: String? = null,
    @SerializedName("text") val text: String? = null
)