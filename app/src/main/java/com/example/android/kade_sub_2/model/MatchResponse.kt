package com.example.android.kade_sub_2.model

import com.google.gson.annotations.SerializedName


data class MatchResponse (
    @SerializedName("events")
    val events: List<MatchModel>
)