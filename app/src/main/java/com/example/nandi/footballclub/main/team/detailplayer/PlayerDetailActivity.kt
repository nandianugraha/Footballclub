package com.example.nandi.footballclub.main.team.detailplayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.main.team.player.PlayerView
import com.example.nandi.footballclub.model.Player
import com.example.nandi.footballclub.model.PlayerDetail
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class PlayerDetailActivity: AppCompatActivity(), PlayerDetailView {

    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var playerId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        val intent = intent
        playerId = intent.getStringExtra("player")
        val gson = Gson()
        val apiRepository = ApiRepository()
        presenter = PlayerDetailPresenter(this, apiRepository, gson)
        presenter.getDetailPlayer(playerId)

    }

    override fun showPlayer(data: List<PlayerDetail>) {
        supportActionBar?.title = data[0].strPlayer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.get().load(data[0].strThumb).into(img_detail)
        player_height.text = data[0].strHeight
        player_weight.text = data[0].strWeight
        player_position.text = data[0].strPosition
        player_overview.text = data[0].strDescriptionEN

    }

    override fun onSupportNavigateUp(): Boolean {
        this.finish()
        return true
    }

}
