package com.example.nandi.footballclub.main.team

import android.app.SearchManager
import com.example.nandi.footballclub.R
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.*
import com.example.nandi.footballclub.R.array.league_name
import com.example.nandi.footballclub.adapter.TeamAdapter
import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.main.team.detailteam.DetailTeamActivity
import com.example.nandi.footballclub.model.Team
import com.example.nandi.footballclub.util.gone
import com.example.nandi.footballclub.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment: Fragment(), TeamView {

    private var teams: MutableList<Team> = mutableListOf()
    private var searchTeam: MutableList<Team> = mutableListOf()
    private var searchView: SearchView? = null

    private lateinit var searchTextListener: SearchView.OnQueryTextListener
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var adapterSearch: TeamAdapter
    private lateinit var spinner: Spinner
    private lateinit var listEvent: RecyclerView
    private lateinit var listSearch: RecyclerView
    private lateinit var leagueName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        val spinnerItems = resources.getStringArray(league_name)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        adapter = TeamAdapter(teams) {
            startActivity<DetailTeamActivity>("team" to "${it.teamId}")
        }

        listEvent.adapter = adapter
        presenter = TeamPresenter(this, request, gson)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                leagueName = leagueName.replace(" ", "%20")
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team, container, false)

        listEvent = view.find(R.id.rc_team)
        listSearch = view.find(R.id.rc_search_team)
        spinner = view.find(R.id.spinner_team)

        listSearch.layoutManager = LinearLayoutManager(activity)
        listEvent.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchManager: SearchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (true) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            searchTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    var searchQuery = query
                    searchQuery.toLowerCase()
                    searchQuery = searchQuery.replace(" ", "_")
                    adapterSearch = TeamAdapter(searchTeam) {
                        startActivity<DetailTeamActivity>("team" to it.teamId)
                    }
                    listSearch.adapter = adapterSearch
                    presenter.getSearchTeam(searchQuery)
                    return true
                }
            }
            searchView?.setOnQueryTextListener(searchTextListener)

        }
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(menuItem: MenuItem): Boolean {
                listSearch.gone()
                listEvent.visible()
                spinner.visible()
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                return false
            }
        }
        searchView?.setOnQueryTextListener(searchTextListener)
        return super.onOptionsItemSelected(item)
    }

    override fun showTeamList(data: List<Team>) {
        listSearch.gone()
        listEvent.visible()
        spinner.visible()

        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showSearch(data: List<Team>) {
        listSearch.visible()
        listEvent.gone()
        spinner.gone()

        searchTeam.clear()
        searchTeam.addAll(data)
        adapter.notifyDataSetChanged()
    }
}