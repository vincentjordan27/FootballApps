package com.example.android.kade_sub_2.api

import com.example.android.kade_sub_2.BuildConfig

object TheSportDBApi {
    fun getPrevMatch(idLeague: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json${BuildConfig.TSDB_API_KEY}eventspastleague.php?id=" + idLeague
    }

    fun getDetailMatch(idEvent: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json${BuildConfig.TSDB_API_KEY}lookupevent.php?id=" + idEvent
    }

    fun getTeamData(idTeam: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json${BuildConfig.TSDB_API_KEY}lookupteam.php?id=" + idTeam
    }

    fun getMatchSearch(query: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json${BuildConfig.TSDB_API_KEY}searchevents.php?e=" + query
    }

    fun getNextMatch(idLeague: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json${BuildConfig.TSDB_API_KEY}eventsnextleague.php?id=" + idLeague
    }

    fun getTeamList(idLeague: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json${BuildConfig.TSDB_API_KEY}lookup_all_teams.php?id=" + idLeague
    }

    fun getStanding(idLeague: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json${BuildConfig.TSDB_API_KEY}lookuptable.php?l=" + idLeague
    }

    fun getTeamSearch(query: String?) : String {
        return "${BuildConfig.BASE_URL}api/v1/json${BuildConfig.TSDB_API_KEY}searchteams.php?t=" + query
    }

}