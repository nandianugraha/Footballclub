package com.example.nandi.footballclub.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {

    val fragmentList = ArrayList<Fragment>()
    val fragmentListTitle = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentListTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentListTitle[position]
    }


}