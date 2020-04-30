package com.example.android.kade_sub_2.view

import com.example.android.kade_sub_2.model.MatchModel

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<MatchModel>)
}