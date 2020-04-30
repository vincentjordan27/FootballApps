package com.example.android.kade_sub_2.presenter

import android.util.Log
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.api.TheSportDBApi
import com.example.android.kade_sub_2.coroutine.CoroutineContextProvide
import com.example.android.kade_sub_2.model.MatchModel
import com.example.android.kade_sub_2.model.MatchSearchResponse
import com.example.android.kade_sub_2.view.MatchSearchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchSearchPresenter(
    private val view: MatchSearchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvide = CoroutineContextProvide()
){

    fun getMatchSearch(query: String){
        view.showLoading()
        GlobalScope.launch (context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(
                    TheSportDBApi.getMatchSearch(
                        query
                    )
                ).await(),
                MatchSearchResponse::class.java)
                var datas : List<MatchModel> ?= null
                datas = data.event?.filter { it.type == "Soccer"}
                Log.i("debugs",datas?.size.toString())
                if(datas?.size.toString() == "0"){
                    view.empty()
                }else
                    datas?.let { view.getMatchSearch(it) }
                view.hideLoading()
        }

    }



}