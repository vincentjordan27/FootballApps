package com.example.android.kade_sub_2.model

import com.google.gson.annotations.SerializedName

data class TeamMatchDataResponse(
    @SerializedName("teams")
    var teams: List<TeamMatchData> ?= null
)