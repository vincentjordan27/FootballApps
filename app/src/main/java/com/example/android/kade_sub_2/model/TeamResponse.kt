package com.example.android.kade_sub_2.model

import com.google.gson.annotations.SerializedName

data class TeamResponse (
    @SerializedName("teams")
    val teams : List<TeamModel> ?= null
)