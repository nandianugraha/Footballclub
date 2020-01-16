package com.example.nandi.footballclub.main.team.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.adapter.PlayerAdapter
import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.main.team.detailplayer.PlayerDetailActivity
import com.example.nandi.footballclub.main.team.detailteam.DetailTeamActivity
import com.example.nandi.footballclub.model.Player
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class PlayerFragment : Fragment(), PlayerView {

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var rvPlayer: RecyclerView
    private lateinit var presenter: PlayerPresenter
    private lateinit var adapter: PlayerAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val teamId = (activity as DetailTeamActivity).getTeamId()
        val gson = Gson()
        val apiRepository = ApiRepository()

        presenter = PlayerPresenter(this, gson, apiRepository)
        adapter = PlayerAdapter(players){
            startActivity<PlayerDetailActivity>("player" to "${it.idPlayer}")
        }

        rvPlayer.adapter = adapter
        presenter.getPlayer(teamId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        rvPlayer = view.find(R.id.rv_player)
        rvPlayer.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun showPlayer(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
        Log.d("cek player fragment", "${Gson().toJsonTree(data)}")

    }

}
