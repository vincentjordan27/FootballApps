package com.example.android.kade_sub_2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MatchModel(

    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? =null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String?= null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("dateEvent")
    var date: String? = null,

    @SerializedName("strTimeLocal")
    var time: String? = null,

    @SerializedName("strSport")
    var type: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("strPostponed")
    var postponed: String? = null

): Parcelable {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID_EVENT: String = "ID_EVENT"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TIME_LOCAL: String = "TIME_LOCAL"
        const val TYPE: String = "TYPE"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val POSTPONED: String = "POSTPONED"
    }
}