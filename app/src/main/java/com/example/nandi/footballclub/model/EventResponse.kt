package com.example.nandi.footballclub.model

import com.google.gson.annotations.SerializedName

data class EventResponse(
        @SerializedName("events")
        val events: List<Event>
)