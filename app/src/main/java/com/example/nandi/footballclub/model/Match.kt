package com.example.nandi.footballclub.model

import com.google.gson.annotations.SerializedName

data class Match(
        @SerializedName("idEvent")
        val idEvent: String? = "",
        @SerializedName("idHomeTeam")
        val idHomeTeam: String? = "",
        @SerializedName("strHomeTeam")
        val strHomeTeam: String? = "",
        @SerializedName("intHomeScore")
        val intHomeScore: String? = "",
        @SerializedName("idAwayTeam")
        val idAwayTeam: String? = "",
        @SerializedName("strAwayTeam")
        val strAwayTeam: String? = "",
        @SerializedName("intAwayScore")
        val intAwayScore: String? = "",
        @SerializedName("dateEvent")
        val dateEvent: String? = "",
        @SerializedName("strTime")
        val strTime: String? = "")