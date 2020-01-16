
package com.example.nandi.footballclub.main.team.detailteam

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.adapter.ViewPagerAdapter
import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.database
import com.example.nandi.footballclub.main.team.TeamPresenter
import com.example.nandi.footballclub.main.team.TeamView
import com.example.nandi.footballclub.main.team.overview.OverviewFragment
import com.example.nandi.footballclub.main.team.player.PlayerFragment
import com.example.nandi.footballclub.model.FavoriteTeam
import com.example.nandi.footballclub.model.Team
import com.example.nandi.footballclub.util.invisible
import com.example.nandi.footballclub.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.intentFor

class DetailTeamActivity: AppCompatActivity(), TeamView {

    private lateinit var presenter: TeamPresenter
    private var teams: MutableList<Team> = mutableListOf()

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        id = intent.getStringExtra("team")
        setSupportActionBar(toolbar_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val request = ApiRepository()
        val gson = Gson()

        favoriteState()

        presenter = TeamPresenter(this, request, gson)
        presenter.getTeamDetail(id)

        setupViewPager(viewpager)
        tabs_detail.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewpager: ViewPager?) {
        val adapterVP = ViewPagerAdapter(supportFragmentManager)
        adapterVP.addFragment(OverviewFragment(), getString(R.string.overview))
        adapterVP.addFragment(PlayerFragment(), getString(R.string.player))
        viewpager?.adapter = adapterVP
    }

    override fun showTeamList(data: List<Team>) {
        teams.clear()
        teams.addAll(data)
        Picasso.get().load(data[0].teamBadge).into(team_badge_detail)
        team_name_detail.text = data[0].teamName
        team_year_detail.text = data[0].teamFormedYear
        team_stadium_detail.text = data[0].teamStadium
    }

    fun getTeamId(): String? {
        return id
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite)removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to teams[0].teamId,
                        FavoriteTeam.TEAM_NAME to teams[0].teamName,
                        FavoriteTeam.TEAM_BADGE to teams[0].teamBadge)
            }
            snackbar(team_container, "Add ${teams[0].teamName} to Team Favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(team_container, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})", "id" to id.toString())
            }
            snackbar(team_container, "Remove ${teams[0].teamName} from Team Favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(team_container , e.localizedMessage).show()
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to id.toString())
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun showSearch(data: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}