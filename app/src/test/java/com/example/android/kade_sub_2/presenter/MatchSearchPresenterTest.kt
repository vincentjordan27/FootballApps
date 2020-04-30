package com.example.android.kade_sub_2.presenter

import com.example.android.kade_sub_2.TestContextProvide
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.model.MatchModel
import com.example.android.kade_sub_2.model.MatchSearchResponse
import com.example.android.kade_sub_2.view.MatchSearchView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchSearchPresenterTest {

    @Mock
    private lateinit var view: MatchSearchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var presenter: MatchSearchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchSearchPresenter(view,apiRepository,gson,
            TestContextProvide()
        )
    }

    @Test
    fun getMatchSearch() {
        val match: MutableList<MatchModel> = mutableListOf()
        val response = MatchSearchResponse(match)
        val query = "arsenal"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(gson.fromJson(
                "",
                MatchSearchResponse::class.java
            )).thenReturn(response)

            presenter.getMatchSearch(query)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).getMatchSearch(match)
            Mockito.verify(view).hideLoading()
        }
    }
}