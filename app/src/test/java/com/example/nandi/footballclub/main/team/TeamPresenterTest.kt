package com.example.nandi.footballclub.main.team

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.Team
import com.example.nandi.footballclub.model.TeamResponse
import com.example.nandi.footballclub.util.CoroutineContextProviderTest
import com.google.gson.Gson
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TeamPresenterTest {

    @Mock
    private lateinit var view: TeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun getTeamList() {
        val league: MutableList<Team> = mutableListOf()
        val response = TeamResponse(league)
        val leagueId = "1234"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeam(leagueId)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.getTeamList(leagueId)
        verify(view).showTeamList(league)
    }

    @Test
    fun getTeamDetail() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val idTeam = "1234"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetail(idTeam)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.getTeamList(idTeam)
        verify(view).showTeamList(teams)
    }

    @Test
    fun getSearchTeam() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val query = "real madrid"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchTeam(query)),
                TeamResponse::class.java)).thenReturn(response)
        presenter.getSearchTeam(query)
        verify(view).showSearch(teams)
    }
}