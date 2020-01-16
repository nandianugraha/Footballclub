package com.example.nandi.footballclub.main.match.detailmatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.R.drawable.ic_add_to_favorites
import com.example.nandi.footballclub.R.drawable.ic_added_to_favorites
import com.example.nandi.footballclub.data.ApiRepository
import com.example.nandi.footballclub.data.database
import com.example.nandi.footballclub.model.Event
import com.example.nandi.footballclub.model.FavoriteMatch
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), DetailView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var presenter: DetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        id = intent.getStringExtra("id")
        val idEvent = id.toInt()
        supportActionBar?.title = "Detail Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.loadDetailMatch(idEvent)
    }

    override fun showDetailMatch(data: List<Event>) {
        supportActionBar?.title = data[0].strEvent
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadHomeLogo(data[0].idHomeTeam)
        presenter.loadAwayLogo(data[0].idAwayTeam)
        events.clear()
        events.addAll(data)

        val dateShort = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(data[0].dateEvent)
        val dateLong = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(dateShort)

        tv_date.text = dateLong.toString()
        tv_time.text = data[0].strTime

        tv_homescore.text = data[0].intHomeScore
        tv_awayscore.text = data[0].intAwayScore

        strHomeShots.text = data[0].intHomeShots
        strAwayShots.text = data[0].intAwayShots

        tv_hometeam.text = data[0].strHomeTeam
        tv_awayteam.text = data[0].strAwayTeam

        strHomeGoalDetail.text = data[0].strHomeGoalDetails
        strAwayGoalDetail.text = data[0].strAwayGoalDetails

        strHomeLineupForward.text = data[0].strHomeLineupForward
        strAwayLineupForward.text = data[0].strAwayLineupForward

        strHomeLineupMidfield.text = data[0].strHomeLineupMidfield
        strAwayLineupMidfield.text = data[0].strAwayLineupMidfield

        strHomeLineupDefense.text = data[0].strHomeLineupDefense
        strAwayLineupDefense.text = data[0].strAwayLineupDefense

        strHomeLineupGoalkeeper.text = data[0].strHomeLineupGoalkeeper
        strAwayLineupGoalkeeper.text = data[0].strAwayLineupGoalkeeper

        strHomeLineupSubtitutes.text = data[0].strHomeLineupSubstitutes
        strAwayLineupSubtitutes.text = data[0].strAwayLineupSubstitutes
    }

    private fun loadImage(url: String, ivTarget: ImageView) {
        Picasso.get().load(url).resize(150, 150).into(ivTarget)
    }

    override fun showAwayLogo(imageUrl: String) {
        if (!imageUrl.isEmpty()) {
            loadImage(imageUrl, away_logo)
        }
    }

    override fun showHomeLogo(imageUrl: String) {
        if (!imageUrl.isEmpty()) {
            loadImage(imageUrl, home_logo)
        }
    }

    private fun favoriteState(){
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                    .whereArgs("(IDEVENT = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (!favorite.isEmpty()) isFavorite = true
        }
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
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.IDEVENT to events[0].idEvent,
                        FavoriteMatch.HOMETEAM to events[0].strHomeTeam,
                        FavoriteMatch.AWAYTEAM to events[0].strAwayTeam,
                        FavoriteMatch.HOMESCORE to events[0].intHomeScore,
                        FavoriteMatch.AWAYSCORE to events[0].intAwayScore,
                        FavoriteMatch.DATEEVENT to events[0].dateEvent,
                        FavoriteMatch.TIME to events[0].strTime,
                        FavoriteMatch.HOMEID to events[0].idHomeTeam,
                        FavoriteMatch.AWAYID to events[0].idAwayTeam)
            }
            snackbar(linear_detail, "Added ${events[0].strHomeTeam} vs ${events[0].strAwayTeam} to FavoriteMatch").show()
        } catch (e: SQLiteConstraintException){
            snackbar(linear_detail, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(IDEVENT = {id})", "id" to id)
            }
            snackbar(linear_detail, "Removed ${events[0].strHomeTeam} vs ${events[0].strAwayTeam} from FavoriteMatch").show()
        } catch (e: SQLiteConstraintException){
            snackbar(linear_detail, e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
}
