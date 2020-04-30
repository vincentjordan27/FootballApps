package com.example.android.kade_sub_2.model

import com.google.gson.annotations.SerializedName

data class StandingModel (
    @SerializedName("name")
    var name: String? = null,

    @SerializedName("played")
    var played: Int? = 0,

    @SerializedName("goalsfor")
    var goalsfor: Int? = 0,

    @SerializedName("goalsagainst")
    var goalsagainst: Int? = 0,

    @SerializedName("goalsdifference")
    var goalsdifference: Int? = 0,

    @SerializedName("win")
    var win: Int? = 0,

    @SerializedName("draw")
    var draw: Int? = 0,

    @SerializedName("loss")
    var loss: Int? = 0,

    @SerializedName("total")
    var total: Int? = 0
)