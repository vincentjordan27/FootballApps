package com.example.android.kade_sub_2.view

import com.example.android.kade_sub_2.model.TeamMatchData

interface TeamSearchView {
    fun showLoading()
    fun hideLoading()
    fun getTeamSearch(data: List<TeamMatchData>)
    fun empty()
}