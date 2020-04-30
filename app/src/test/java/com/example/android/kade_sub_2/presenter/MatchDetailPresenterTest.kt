package com.example.android.kade_sub_2.presenter

import com.example.android.kade_sub_2.TestContextProvide
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.model.MatchDetailModel
import com.example.android.kade_sub_2.model.MatchDetailResponse
import com.example.android.kade_sub_2.view.MatchDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock
    private lateinit var view: MatchDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view,apiRepository,gson,
            TestContextProvide()
        )
    }

    @Test
    fun getMatchDetail() {
        val match: MutableList<MatchDetailModel> = mutableListOf()
        val response = MatchDetailResponse(match)
        val idEvent = "610898"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson(
                "",
                MatchDetailResponse::class.java
            )).thenReturn(response)

            presenter.getMatchDetail(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).matchDetail(match)
            Mockito.verify(view).hideLoading()
        }
    }
}