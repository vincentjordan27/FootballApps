package com.example.android.kade_sub_2.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamModel (
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var strTeam: String? = null,

    @SerializedName("strLeague")
    var strLeague: String? =null,

    @SerializedName("strTeamBadge")
    var strTeamBadge: String? = null

) : Parcelable {

    companion object {
        const val TEAM_FAVORITE : String = "TEAM_FAVORITE"
        const val ID_TEAM : String = "ID_TEAM"
        const val STR_TEAM : String = "STR_TEAM"
        const val STR_LEAGUE : String = "STR_LEAGUE"
        const val STR_TEAM_BADGE : String = "STR_TEAM_BADGE"
    }
}