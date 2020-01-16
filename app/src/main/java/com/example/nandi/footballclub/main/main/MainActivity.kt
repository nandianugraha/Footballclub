package com.example.nandi.footballclub.main.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.nandi.footballclub.*
import com.example.nandi.footballclub.main.favorite.FavoriteFragment
import com.example.nandi.footballclub.main.favorite.favoritematch.FavoriteMatchFragment
import com.example.nandi.footballclub.main.match.MatchFragment
import com.example.nandi.footballclub.main.team.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_main)
        supportActionBar?.title = resources.getString(R.string.match)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.match -> {
                    loadMatchFragment(savedInstanceState)
                    supportActionBar?.title = getString(R.string.match)
                }
                R.id.team -> {
                    loadTeamFragment(savedInstanceState)
                    supportActionBar?.title = getString(R.string.team)
                }
                R.id.favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                    supportActionBar?.title = getString(R.string.favorites)

                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.match
    }
    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }
}
