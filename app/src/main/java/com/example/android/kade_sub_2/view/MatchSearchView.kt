package com.example.android.kade_sub_2.view

import com.example.android.kade_sub_2.model.MatchModel

interface MatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun getMatchSearch(data: List<MatchModel>)
    fun empty()
}