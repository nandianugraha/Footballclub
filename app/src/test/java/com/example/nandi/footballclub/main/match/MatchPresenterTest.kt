package com.example.nandi.footballclub.main.match

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.Match
import com.example.nandi.footballclub.model.MatchResponse
import com.example.nandi.footballclub.model.SearchMatchResponse
import com.example.nandi.footballclub.util.CoroutineContextProviderTest
import com.google.gson.Gson
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun loadNextMatch() {
        val nextMatch: MutableList<Match> = mutableListOf()
        val response = MatchResponse(nextMatch)
        val idEvent = "12345"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch(idEvent)),
                MatchResponse::class.java)).thenReturn(response)
        presenter.loadNextMatch(idEvent)
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showMatchList(nextMatch)
    }

    @Test
    fun loadLastMatch() {
        val lastMatch: MutableList<Match> = mutableListOf()
        val response = MatchResponse(lastMatch)
        val idEvent = "12345"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatch(idEvent)),
                MatchResponse::class.java)).thenReturn(response)
        presenter.loadNextMatch(idEvent)
        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showMatchList(lastMatch)
    }

    @Test
    fun loadSearch() {
        val event: MutableList<Match> = mutableListOf()
        val response = SearchMatchResponse(event)
        val query = "asdasdsa"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchMatch(query)),
                SearchMatchResponse::class.java)).thenReturn(response)
        presenter.loadSearch(query)
    }
}