package com.example.nandi.footballclub.main.match.lastmatch

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.R.array.league_name
import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.adapter.MainAdapter
import com.example.nandi.footballclub.main.match.detailmatch.DetailActivity
import com.example.nandi.footballclub.main.match.MatchPresenter
import com.example.nandi.footballclub.main.match.MatchView
import com.example.nandi.footballclub.model.Event
import com.example.nandi.footballclub.model.Match
import com.example.nandi.footballclub.util.invisible
import com.example.nandi.footballclub.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

class LastFragment : Fragment(), AnkoComponent<Context>, MatchView {

    private var events: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var spinner: Spinner
    private lateinit var adapter: MainAdapter
    private lateinit var rvEvent: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = MainAdapter(events) {
            ctx.startActivity<DetailActivity>("id" to it.idEvent)
        }
        rvEvent.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(league_name)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val leagueName = spinner.selectedItem.toString()
                when (leagueName) {
                    getString(R.string.epl) -> {
                        presenter.loadLastMatch(getString(R.string.id_epl))
                    }
                    getString(R.string.laliga) -> {
                        presenter.loadLastMatch(getString(R.string.id_laliga))
                    }
                    getString(R.string.bundesliga) -> {
                        presenter.loadLastMatch(getString(R.string.id_bundesliga))
                    }
                    getString(R.string.seriea) -> {
                        presenter.loadLastMatch(getString(R.string.id_serie_a))
                    }
                    getString(R.string.ligue1) -> {
                        presenter.loadLastMatch(getString(R.string.id_ligue1))
                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner{
                id = R.id.spinner_match
            }
            relativeLayout{
                lparams (width = matchParent, height = wrapContent)

                rvEvent = recyclerView {
                    id = R.id.rc_match
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                progressBar = progressBar {
                }.lparams{
                    centerHorizontally()
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showMatchList(data: List<Match>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showSearch(data: List<Match>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}