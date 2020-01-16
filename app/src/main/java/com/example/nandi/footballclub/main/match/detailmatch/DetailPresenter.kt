package com.example.nandi.footballclub.main.match.detailmatch

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.EventResponse
import com.example.nandi.footballclub.model.TeamResponse
import com.example.nandi.footballclub.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPresenter(private val view: DetailView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun loadDetailMatch(eventId: Int?) {
         async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getDetailMatch(eventId)),
                        EventResponse::class.java)
            }
                view.showDetailMatch(data.await().events)
         }
    }

    fun loadHomeLogo(idHomeTeam: String?){
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(idHomeTeam)),
                        TeamResponse::class.java)
            }
                view.showHomeLogo(data.await().teams[0].teamBadge!!)
        }
    }

    fun loadAwayLogo(idAwayTeam: String?){
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(idAwayTeam)),
                        TeamResponse::class.java)
            }
                view.showAwayLogo(data.await().teams[0].teamBadge!!)
            }
        }
    }