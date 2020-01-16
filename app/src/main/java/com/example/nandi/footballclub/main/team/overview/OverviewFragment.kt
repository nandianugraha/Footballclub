package com.example.nandi.footballclub.main.team.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.main.team.TeamPresenter
import com.example.nandi.footballclub.main.team.TeamView
import com.example.nandi.footballclub.main.team.detailteam.DetailTeamActivity
import com.example.nandi.footballclub.model.Team
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.fragment_overview.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.intentFor

class OverviewFragment : Fragment(), TeamView {

    private var teams: MutableList<Team> = mutableListOf()

    private lateinit var tvOverview: TextView
    private lateinit var presenter: TeamPresenter
    private var teamId: String? = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        teamId = (activity as DetailTeamActivity).getTeamId()
        val gson = Gson()
        val apiRepository = ApiRepository()

        presenter = TeamPresenter(this, apiRepository, gson)
        presenter.getTeamDetail(teamId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        tvOverview = view.find(R.id.tv_overview)
        return view
    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        tv_overview.text = data[0].teamDescription
        Log.d("cek data fragment", "${Gson().toJsonTree(data)}")

    }

    override fun showSearch(data: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}