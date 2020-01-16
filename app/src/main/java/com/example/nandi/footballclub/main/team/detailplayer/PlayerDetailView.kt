package com.example.nandi.footballclub.main.team.detailplayer

import com.example.nandi.footballclub.model.Player
import com.example.nandi.footballclub.model.PlayerDetail

interface PlayerDetailView {

    fun showPlayer(data: List<PlayerDetail>)
}