package com.example.android.kade_sub_2.presenter

import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.api.TheSportDBApi
import com.example.android.kade_sub_2.coroutine.CoroutineContextProvide
import com.example.android.kade_sub_2.model.StandingResponse
import com.example.android.kade_sub_2.view.StandingView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingPresenter(
    private val view: StandingView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvide = CoroutineContextProvide()
) {
    fun getStandingTable(id: String?){
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getStanding(id))
                .await(),StandingResponse::class.java)

            data.table?.let { view.getStanding(it) }
            view.hideLoading()

        }
    }
}