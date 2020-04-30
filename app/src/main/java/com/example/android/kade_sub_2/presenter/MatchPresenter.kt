package com.example.android.kade_sub_2.presenter

import com.example.android.kade_sub_2.model.MatchResponse
import com.example.android.kade_sub_2.view.MatchView
import com.example.android.kade_sub_2.api.TheSportDBApi
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.coroutine.CoroutineContextProvide
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MatchPresenter (
    private var view: MatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvide = CoroutineContextProvide()
){

    private var views: MatchView ?= null

    init {
        this.views = view
    }
    fun getMatchList(idLeague: String?){
        views?.showLoading()
        GlobalScope.launch (context.main){
            val data = gson.fromJson(apiRepository
                .doRequest(
                    TheSportDBApi.getPrevMatch(
                        idLeague
                    )
                ).await(),
                MatchResponse::class.java
            )

            data.events.let { views?.showMatchList(it) }
            views?.hideLoading()

        }

    }

    fun getMatchNextList(idLeague: String?){
        views?.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(
                        TheSportDBApi.getNextMatch(
                            idLeague
                        )
                    ).await(),
                MatchResponse::class.java
            )

            data.events.let { views?.showMatchList(it) }
            views?.hideLoading()
        }

    }

    fun onDetach() {
       views = null
    }


}