package com.example.nandi.footballclub.main.match.detailmatch

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.Event
import com.example.nandi.footballclub.model.EventResponse
import com.example.nandi.footballclub.model.Team
import com.example.nandi.footballclub.model.TeamResponse
import com.example.nandi.footballclub.util.CoroutineContextProviderTest
import com.google.gson.Gson
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class DetailPresenterTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun loadDetailMatch() {

        val detailMatch: MutableList<Event> = mutableListOf()
        val response = EventResponse(detailMatch)
        val idEvent = 12345

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailMatch(idEvent)),
                EventResponse::class.java)).thenReturn(response)
        presenter.loadDetailMatch(idEvent)
        verify(view).showDetailMatch(detailMatch)

    }

    @Test
    fun loadHomeLogo() {

        val homeLogo: MutableList<Team> = mutableListOf()
        val response = TeamResponse(homeLogo)
        val idHomeTeam = "123"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(idHomeTeam)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.loadHomeLogo(idHomeTeam)
    }

    @Test
    fun loadAwayLogo() {
        val awayLogo: MutableList<Team> = mutableListOf()
        val response = TeamResponse(awayLogo)
        val idAwayTeam = "123"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(idAwayTeam)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.loadAwayLogo(idAwayTeam)
    }
}