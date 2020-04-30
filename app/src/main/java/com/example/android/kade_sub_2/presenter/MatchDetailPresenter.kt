package com.example.android.kade_sub_2.presenter

import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.model.TeamMatchDataResponse
import com.example.android.kade_sub_2.api.TheSportDBApi
import com.example.android.kade_sub_2.coroutine.CoroutineContextProvide
import com.example.android.kade_sub_2.model.MatchDetailResponse
import com.example.android.kade_sub_2.view.MatchDetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter (
    private val view: MatchDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvide = CoroutineContextProvide()
){
    fun getMatchDetail(idevent: String){
            view.showLoading()
            GlobalScope.launch(context.main) {
                val data = gson.fromJson(apiRepository
                    .doRequest(
                        TheSportDBApi.getDetailMatch(
                            idevent
                        )
                    ).await(),
                    MatchDetailResponse::class.java)

                data.events.let { view.matchDetail(it) }
                view.hideLoading()
            }

        }


    fun getHomeData(idTeam: String){
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getTeamData(
                            idTeam
                        )
                    ).await(),
                TeamMatchDataResponse::class.java)

            data.teams?.let { view.homeTeam(it) }
            view.hideLoading()
        }

    }

    fun getAwayData(idTeam: String){

        GlobalScope.launch (context.main){
            val data = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getTeamData(
                            idTeam
                        )
                    ).await(),
                TeamMatchDataResponse::class.java)

            data.teams?.let { view.awayTeam(it) }

        }
    }

}