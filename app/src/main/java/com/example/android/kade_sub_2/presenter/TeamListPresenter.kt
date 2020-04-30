package com.example.android.kade_sub_2.presenter

import android.util.Log
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.api.TheSportDBApi
import com.example.android.kade_sub_2.coroutine.CoroutineContextProvide
import com.example.android.kade_sub_2.model.TeamResponse
import com.example.android.kade_sub_2.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamListPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvide = CoroutineContextProvide()
) {
    fun getTeamList(idLeague: String?){
        view.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamList(idLeague))
                .await(),
            TeamResponse::class.java)

            Log.i("debugs", data.teams?.size.toString())
            data.teams?.let { view.getTeamList(it) }
            view.hideLoading()
        }

    }
}