package com.example.android.kade_sub_2.model

import com.google.gson.annotations.SerializedName

data class TeamMatchData(
    @SerializedName("idTeam")
    var idTeam: String ?= null,

    @SerializedName("strTeam")
    var teamName: String ?= null,

    @SerializedName("strTeamBadge")
    var teamBadge: String ?= null,

    @SerializedName("strDescriptionEN")
    var teamDesc: String ?= null,

    @SerializedName("intFormedYear")
    var teamYear: String ?= null,

    @SerializedName("strStadium")
    var teamStadium: String ?= null,

    @SerializedName("strSport")
    var type: String ?= null,

    @SerializedName("strLeague")
    var strLeague: String? =null

)