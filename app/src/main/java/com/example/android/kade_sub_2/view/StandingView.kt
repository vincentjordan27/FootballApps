package com.example.android.kade_sub_2.view

import com.example.android.kade_sub_2.model.StandingModel

interface StandingView {
    fun showLoading()
    fun getStanding(data : List<StandingModel>)
    fun hideLoading()
}