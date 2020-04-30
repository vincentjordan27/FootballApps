package com.example.android.kade_sub_2.presenter

import android.util.Log
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.api.TheSportDBApi
import com.example.android.kade_sub_2.coroutine.CoroutineContextProvide
import com.example.android.kade_sub_2.model.TeamMatchData
import com.example.android.kade_sub_2.model.TeamMatchDataResponse
import com.example.android.kade_sub_2.view.TeamSearchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamSearchPresenter (
    private val view: TeamSearchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvide = CoroutineContextProvide()
) {
    fun getTeamSearch(query: String){
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamSearch(query)).await(),
            TeamMatchDataResponse::class.java)

            var datas : List<TeamMatchData> ?= null
            datas = data.teams?.filter { it.type == "Soccer" }
            Log.i("debugs",datas?.size.toString())
            if(datas == null){
                view.empty()
            }else
                datas.let { view.getTeamSearch(it) }
            view.hideLoading()
        }
    }
}