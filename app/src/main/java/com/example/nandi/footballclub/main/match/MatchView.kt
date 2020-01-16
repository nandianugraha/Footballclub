package com.example.nandi.footballclub.main.match

import com.example.nandi.footballclub.model.Event
import com.example.nandi.footballclub.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
    fun showSearch(data: List<Match>)
}