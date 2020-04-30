package com.example.android.kade_sub_2.model

import com.google.gson.annotations.SerializedName

data class StandingResponse (
    @SerializedName("table")
    val table : List<StandingModel> ?= null
)