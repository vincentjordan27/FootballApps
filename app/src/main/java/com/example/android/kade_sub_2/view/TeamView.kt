package com.example.android.kade_sub_2.view

import com.example.android.kade_sub_2.model.TeamModel

interface TeamView {
    fun showLoading()
    fun getTeamList(data : List<TeamModel>)
    fun hideLoading()
}