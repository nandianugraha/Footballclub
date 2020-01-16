package com.example.nandi.footballclub.main.team.player

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.Player
import com.example.nandi.footballclub.model.PlayerResponse
import com.example.nandi.footballclub.util.CoroutineContextProviderTest
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayerPresenterTest {

    @Mock
    private lateinit var view: PlayerView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, gson, apiRepository, CoroutineContextProviderTest())
    }

    @Test
    fun getPlayer() {
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(players)
        val teamId = "1234"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayer(teamId)),
                PlayerResponse::class.java)).thenReturn(response)
        presenter.getPlayer(teamId)
        verify(view).showPlayer(players)
    }
}