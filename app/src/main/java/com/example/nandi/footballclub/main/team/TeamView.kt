package com.example.nandi.footballclub.main.team

import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.model.Team
import com.example.nandi.footballclub.util.CoroutineContextProvider
import com.google.gson.Gson

interface TeamView{
    fun showTeamList(data: List<Team>)
    fun showSearch(data: List<Team>)
}