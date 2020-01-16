package com.example.nandi.footballclub.main.team.player

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.PlayerResponse
import com.example.nandi.footballclub.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val gson: Gson,
                      private val apiRepository: ApiRepository,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayer(teamId: String?){
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getPlayer(teamId)),
                        PlayerResponse::class.java)
            }
            view.showPlayer(data.await().player)
        }
    }
}