package com.example.nandi.footballclub.model

data class FavoriteMatch(
        val id: Long?,
        val idEvent: String?,
        val strHomeTeam: String?,
        val strAwayTeam: String?,
        val intHomeScore: String?,
        val intAwayScore: String?,
        val strHomeRedCards: String?,
        val strHomeYellowCards: String?,
        val strHomeLineupGoalkeeper: String?,
        val strHomeLineupDefense: String?,
        val strHomeLineupMidfield: String?,
        val strHomeLineupForward: String?,
        val strHomeLineupSubstitutes: String?,
        val strHomeFormation: String?,
        val strAwayRedCards: String?,
        val strAwayYellowCards: String?,
        val strAwayLineupGoalkeeper: String?,
        val strAwayLineupDefense: String?,
        val strAwayLineupMidfield: String?,
        val strAwayLineupForward: String?,
        val strAwayLineupSubstitutes: String?,
        val strAwayFormation: String?,
        val intHomeShots: String?,
        val intAwayShots: String?,
        val dateEvent: String?,
        val strTime: String?,
        val idHomeTeam: String?,
        val idAwayTeam: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val IDEVENT: String = "IDEVENT"
        const val HOMETEAM: String = "HOMETEAM"
        const val AWAYTEAM: String = "AWAYTEAM"
        const val HOMESCORE: String = "HOMESCORE"
        const val AWAYSCORE: String = "AWAYSCORE"
        const val HOMERED: String = "HOMERED"
        const val HOMEYELLOW: String = "HOMEYELLOW"
        const val HOMEGK: String = "HOMEGK"
        const val HOMEDF: String = "HOMEDF"
        const val HOMEMF: String = "HOMEMF"
        const val HOMEFW: String = "HOMEFW"
        const val HOMESUB: String = "HOMESUB"
        const val HOMEFORMATION: String = "HOMEFORMATION"
        const val AWAYRED: String = "AWAYRED"
        const val AWAYYELLOW: String = "AWAYYELLOW"
        const val AWAYGK: String = "AWAYGK"
        const val AWAYDF: String = "AWAYDF"
        const val AWAYMF: String = "AWAYMF"
        const val AWAYFW: String = "AWAYFW"
        const val AWAYSUB: String = "AWAYSUB"
        const val AWAYFORMATION: String = "AWAYFORMATION"
        const val HOMESHOTS: String = "HOMESHOTS"
        const val AWAYSHOTS: String = "AWAYSHOTS"
        const val DATEEVENT: String = "DATEEVENT"
        const val TIME: String = "TIME"
        const val HOMEID: String = "HOMEID"
        const val AWAYID: String = "AWAYID"
        const val HOMEBADGE: String = "HOMEBADGE"
        const val AWAYBADGE: String = "AWAYBADGE"
    }
}