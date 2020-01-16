package com.example.nandi.footballclub.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("idTeam")
        var teamId: String? = "",
        @SerializedName("strTeam")
        var teamName: String? = "",
        @SerializedName("strTeamBadge")
        var teamBadge: String? = "",
        @SerializedName("intFormedYear")
        val teamFormedYear: String? = "",
        @SerializedName("strManager")
        val teamManager: String? = "",
        @SerializedName("strStadium")
        val teamStadium: String? = "",
        @SerializedName("strDescriptionEN")
        val teamDescription: String? = "")