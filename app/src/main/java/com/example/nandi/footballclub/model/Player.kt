package com.example.nandi.footballclub.model

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("idPlayer")
        val idPlayer: String? = "",
        @SerializedName("idTeam")
        val idTeam: String? = "",
        @SerializedName("strPlayer")
        val strPlayer: String? = "",
        @SerializedName("strPosition")
        val strPosition: String? = "",
        @SerializedName("strCutout")
        val strCutout: String? = "")