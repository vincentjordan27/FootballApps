package com.example.android.kade_sub_2.presenter

import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.api.TheSportDBApi
import com.example.android.kade_sub_2.coroutine.CoroutineContextProvide
import com.example.android.kade_sub_2.model.TeamDetailResponse
import com.example.android.kade_sub_2.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(
    private val view: TeamDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvide = CoroutineContextProvide()
) {
    fun getTeamDetail(idTeam: String?){
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamData(idTeam))
                .await(),TeamDetailResponse::class.java)

            view.getTeamData(data.teams)
            view.hideLoading()
        }
    }
}