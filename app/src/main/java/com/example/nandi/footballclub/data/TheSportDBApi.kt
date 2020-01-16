package com.example.nandi.footballclub.data

import com.example.nandi.footballclub.BuildConfig

object TheSportDBApi {

    //match
    fun getLastMatch(leagueId: String?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=$leagueId"
    }
    fun getNextMatch(leagueId: String?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=$leagueId"
    }
    fun getDetailMatch(eventId: Int?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=$eventId"
    }
    fun getSearchMatch(query: String?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/searchevents.php?e=$query"
    }

    //team
    fun getTeam(league: String?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l=" + league
    }
    fun getPlayer(idTeam: String?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/lookup_all_players.php?id=" + idTeam
    }
    fun getPlayerDetail(idPlayer: String?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/lookupplayer.php?id=" + idPlayer
    }
    fun getTeamDetail(teamId: String?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/lookupteam.php?id=" + teamId
    }
    fun getSearchTeam(url: String?): String{
        return BuildConfig.BASE_URL + "${BuildConfig.TSDB_API_KEY}/searchteams.php?t=$url"
    }
}