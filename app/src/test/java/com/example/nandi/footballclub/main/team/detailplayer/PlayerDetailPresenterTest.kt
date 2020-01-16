package com.example.nandi.footballclub.main.team.detailplayer

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.PlayerDetail
import com.example.nandi.footballclub.model.PlayerDetailResponse
import com.example.nandi.footballclub.util.CoroutineContextProviderTest
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class PlayerDetailPresenterTest {

    @Mock
    private lateinit var view: PlayerDetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerDetailPresenter

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = PlayerDetailPresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun getDetailPlayer() {
        val players: MutableList<PlayerDetail> = mutableListOf()
        val response = PlayerDetailResponse(players)
        val playerId = "1234"

        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayerDetail(playerId)),
                PlayerDetailResponse::class.java)).thenReturn(response)
        presenter.getDetailPlayer(playerId)
        verify(view).showPlayer(players)
    }
}