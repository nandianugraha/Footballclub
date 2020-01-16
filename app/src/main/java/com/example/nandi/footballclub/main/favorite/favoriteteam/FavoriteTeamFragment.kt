package com.example.nandi.footballclub.main.favorite.favoriteteam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.adapter.FavoriteTeamAdapter
import com.example.nandi.footballclub.data.database
import com.example.nandi.footballclub.main.team.detailteam.DetailTeamActivity
import com.example.nandi.footballclub.model.FavoriteMatch
import com.example.nandi.footballclub.model.FavoriteTeam
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class FavoriteTeamFragment : Fragment() {

    private lateinit var rvTeamFavorite: RecyclerView
    private lateinit var adapter: FavoriteTeamAdapter
    private var teamFavorite: MutableList<FavoriteTeam> = mutableListOf()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteTeamAdapter(teamFavorite){
            startActivity<DetailTeamActivity>("team" to it.teamId)
        }

        rvTeamFavorite.adapter = adapter
        showTeam()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_team_favorite, container, false)

        rvTeamFavorite = view.find(R.id.rv_team_favorite)
        rvTeamFavorite.layoutManager = LinearLayoutManager(activity)

        return view

    }

    private fun showTeam() {
        context?.database?.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            teamFavorite.clear()
            teamFavorite.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        showTeam()
        adapter.notifyDataSetChanged()
    }

}
