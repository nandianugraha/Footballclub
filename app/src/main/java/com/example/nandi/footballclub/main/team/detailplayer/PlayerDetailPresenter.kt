package com.example.nandi.footballclub.main.team.detailplayer

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.main.team.player.PlayerView
import com.example.nandi.footballclub.model.PlayerDetail
import com.example.nandi.footballclub.model.PlayerDetailResponse
import com.example.nandi.footballclub.model.PlayerResponse
import com.example.nandi.footballclub.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerDetailPresenter(private val view: PlayerDetailView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailPlayer(idPlayer: String?){
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayerDetail(idPlayer)),
                        PlayerDetailResponse::class.java)
            }
            view.showPlayer(data.await().players)
        }
    }
}