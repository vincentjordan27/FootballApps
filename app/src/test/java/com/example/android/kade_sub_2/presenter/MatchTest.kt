package com.example.android.kade_sub_2.presenter

import com.example.android.kade_sub_2.TestContextProvide
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.model.MatchModel
import com.example.android.kade_sub_2.model.MatchResponse
import com.example.android.kade_sub_2.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(
            view, apiRepository, gson,
            TestContextProvide()
        )
    }

    @Test
    fun getPrevMatchList() {
        val match: MutableList<MatchModel> = mutableListOf()
        val response = MatchResponse(match)
        val idLeague = "610898"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchList(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(match)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getNextMatchList() {
        val match: MutableList<MatchModel> = mutableListOf()
        val response = MatchResponse(match)
        val idLeague = "610898"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchNextList(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchList(match)
            Mockito.verify(view).hideLoading()
        }
    }
}