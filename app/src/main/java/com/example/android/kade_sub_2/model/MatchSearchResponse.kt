package com.example.android.kade_sub_2.model

import com.google.gson.annotations.SerializedName

data class MatchSearchResponse (
    @SerializedName("event")
    val event: List<MatchModel> ?= null
)