package com.example.nandi.footballclub.main.team

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.PlayerResponse
import com.example.nandi.footballclub.model.TeamResponse
import com.example.nandi.footballclub.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun getTeamList(league: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeam(league)),
                        TeamResponse::class.java)
            }
            view.showTeamList(data.await().teams)
        }
    }

    fun getTeamDetail(teamId: String?) {
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(teamId)),
                        TeamResponse::class.java)
            }
            view.showTeamList(data.await().teams)
        }
    }

    fun getSearchTeam(query: String?){
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getSearchTeam(query)),
                        TeamResponse::class.java)
            }
            view.showSearch(data.await().teams)
        }
    }
}