package com.example.nandi.footballclub.main.match

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.ProgressBar
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.adapter.MainAdapter
import com.example.nandi.footballclub.adapter.ViewPagerAdapter
import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.main.match.detailmatch.DetailActivity
import com.example.nandi.footballclub.main.match.lastmatch.LastFragment
import com.example.nandi.footballclub.main.match.nextmatch.NextFragment
import com.example.nandi.footballclub.model.Match
import com.example.nandi.footballclub.util.gone
import com.example.nandi.footballclub.util.invisible
import com.example.nandi.footballclub.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment: Fragment(), MatchView {

    private var event: MutableList<Match> = mutableListOf()
    private var searchView: SearchView? = null
    private lateinit var searchTextListener: SearchView.OnQueryTextListener
    private lateinit var progressBar: ProgressBar
    private lateinit var rvSearch: RecyclerView
    private lateinit var presenter: MatchPresenter
    private lateinit var tabMatch: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        rvSearch.gone()
        tabMatch.visible()
        viewPager.visible()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_match, container, false)
        tabMatch = view.find(R.id.match_tab)
        viewPager = view.find(R.id.view_pager_match)
        rvSearch = view.find(R.id.rv_search)
        setupViewPager(viewPager)
        tabMatch.setupWithViewPager(viewPager)
        rvSearch.layoutManager = LinearLayoutManager(this.activity)
        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(LastFragment(), getString(R.string.last_match))
        adapter.addFragment(NextFragment(), getString(R.string.next_match))
        viewPager.adapter = adapter
    }
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSearch(data: List<Match>) {
        rvSearch.visible()
        tabMatch.gone()
        viewPager.gone()

        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()
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
                    var querySearch = query
                    querySearch.toLowerCase()
                    querySearch = querySearch.replace(" ", "_")
                    adapter = MainAdapter(event) {
                        startActivity<DetailActivity>("id" to it.idEvent)
                    }
                    rvSearch.adapter = adapter
                    presenter.loadSearch(querySearch)
                    return true
                }
            }
            searchView?.setOnQueryTextListener(searchTextListener)
        }
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                rvSearch.gone()
                viewPager.visible()
                tabMatch.visible()
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_search -> {
                return false
            }
        }
        searchView?.setOnQueryTextListener(searchTextListener)
        return super.onOptionsItemSelected(item)
    }
}