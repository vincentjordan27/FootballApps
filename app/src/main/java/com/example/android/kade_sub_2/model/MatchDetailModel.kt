package com.example.android.kade_sub_2.model

import com.google.gson.annotations.SerializedName

data class MatchDetailModel (
    @SerializedName("intHomeScore")
    var homeScore: String?,

    @SerializedName("intAwayScore")
    var awayScore: String?,

    @SerializedName("strHomeGoalDetails")
    var homeGoal: String?,

    @SerializedName("strHomeLineupGoalkeeper")
    var gkHome: String?,

    @SerializedName("strHomeLineupDefense")
    var defHome: String?,

    @SerializedName("strHomeLineupMidfield")
    var midHome: String?,

    @SerializedName("strHomeLineupForward")
    var fowHome: String?,

    @SerializedName("strHomeLineupSubstitutes")
    var subsHome: String?,

    @SerializedName("strAwayGoalDetails")
    var awayGoal: String?,

    @SerializedName("strAwayLineupGoalkeeper")
    var gkAway: String?,

    @SerializedName("strAwayLineupDefense")
    var defAway: String?,

    @SerializedName("strAwayLineupMidfield")
    var midAway: String?,

    @SerializedName("strAwayLineupForward")
    var fowAway: String?,

    @SerializedName("strAwayLineupSubstitutes")
    var subsAway: String?

)