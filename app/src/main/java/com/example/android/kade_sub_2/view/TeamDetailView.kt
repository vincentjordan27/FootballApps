package com.example.android.kade_sub_2.view

import com.example.android.kade_sub_2.model.TeamMatchData

interface TeamDetailView {
    fun hideLoading()
    fun getTeamData(data: List<TeamMatchData>)
    fun showLoading()
}