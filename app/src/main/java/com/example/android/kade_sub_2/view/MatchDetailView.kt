package com.example.android.kade_sub_2.view

import com.example.android.kade_sub_2.model.TeamMatchData
import com.example.android.kade_sub_2.model.MatchDetailModel

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun matchDetail(data: List<MatchDetailModel>)
    fun homeTeam(data: List<TeamMatchData>)
    fun awayTeam(data: List<TeamMatchData>)
}