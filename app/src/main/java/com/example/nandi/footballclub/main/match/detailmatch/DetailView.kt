package com.example.nandi.footballclub.main.match.detailmatch

import com.example.nandi.footballclub.model.Event

interface DetailView{
    fun showDetailMatch(data: List<Event>)
    fun showAwayLogo(imageUrl: String)
    fun showHomeLogo(imageUrl: String)
}