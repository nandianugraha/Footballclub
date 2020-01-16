package com.example.nandi.footballclub.model

import com.google.gson.annotations.SerializedName

data class PlayerDetail(
        @SerializedName("idPlayer")
        val idPlayer: String? = "",
        @SerializedName("strPlayer")
        val strPlayer: String? = "",
        @SerializedName("strHeight")
        val strHeight: String? = "",
        @SerializedName("strWeight")
        val strWeight: String? = "",
        @SerializedName("strPosition")
        val strPosition: String? = "",
        @SerializedName("strDescriptionEN")
        val strDescriptionEN: String? = "",
        @SerializedName("strThumb")
        val strThumb: String? = "")