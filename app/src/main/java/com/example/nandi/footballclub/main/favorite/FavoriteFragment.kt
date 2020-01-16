package com.example.nandi.footballclub.main.favorite

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.adapter.ViewPagerAdapter
import com.example.nandi.footballclub.main.favorite.favoritematch.FavoriteMatchFragment
import com.example.nandi.footballclub.main.favorite.favoriteteam.FavoriteTeamFragment
import org.jetbrains.anko.find

class FavoriteFragment: Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        tabLayout = view.find(R.id.favorite_tab)
        viewPager = view.find(R.id.view_pager_favorite)
        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapterViewPager = ViewPagerAdapter(childFragmentManager)
        adapterViewPager.addFragment(FavoriteMatchFragment(), getString(R.string.match))
        adapterViewPager.addFragment(FavoriteTeamFragment(), getString(R.string.team))
        viewPager.adapter = adapterViewPager
    }


}