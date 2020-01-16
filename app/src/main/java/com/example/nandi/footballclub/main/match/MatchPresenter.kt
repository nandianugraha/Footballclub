package com.example.nandi.footballclub.main.match

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.TheSportDBApi
import com.example.nandi.footballclub.model.EventResponse
import com.example.nandi.footballclub.model.MatchResponse
import com.example.nandi.footballclub.model.SearchMatchResponse
import com.example.nandi.footballclub.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view: MatchView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson,
                     private val context: CoroutineContextProvider = CoroutineContextProvider()) {
    fun loadNextMatch(leagueId: String?) {
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getNextMatch(leagueId)),
                        MatchResponse::class.java)
            }
            view.hideLoading()
            view.showMatchList(data.await().events)
        }
    }

    fun loadLastMatch(leagueId: String?) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getLastMatch(leagueId)),
                        MatchResponse::class.java)
            }
            view.hideLoading()
            view.showMatchList(data.await().events)
        }
    }
    fun loadSearch(query: String?) {
        //with anko coroutine
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchMatch(query)),
                        SearchMatchResponse::class.java)
            }
            view.showSearch(data.await().event)
        }
    }
}