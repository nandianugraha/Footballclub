package com.example.nandi.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.model.FavoriteMatch
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*

class FavoriteAdapter(private val favoriteMatch: List<FavoriteMatch>, private val listener: (FavoriteMatch) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favoriteMatch[position], listener)
    }

    override fun getItemCount(): Int = favoriteMatch.size

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val dateMatch: TextView = itemView.findViewById(R.id.tv_date)
    private val timeMatch: TextView = itemView.findViewById(R.id.tv_time)
    private val homeTeam: TextView = itemView.findViewById(R.id.tv_hometeam)
    private val awayTeam: TextView = itemView.findViewById(R.id.tv_awayteam)
    private val homeScore: TextView = itemView.findViewById(R.id.tv_homescore)
    private val awayScore: TextView = itemView.findViewById(R.id.tv_awayscore)

    fun bindItem(favoriteMatch: FavoriteMatch, listener: (FavoriteMatch) -> Unit) {

        val dateShort = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        .parse(favoriteMatch.dateEvent)
        val dateLong = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(dateShort)

        dateMatch.text = dateLong.toString()
        timeMatch.text = favoriteMatch.strTime
        homeTeam.text = favoriteMatch.strHomeTeam
        awayTeam.text = favoriteMatch.strAwayTeam
        homeScore.text = favoriteMatch.intHomeScore ?: ""
        awayScore.text = favoriteMatch.intAwayScore ?: ""

        itemView.onClick { listener(favoriteMatch) }
    }
}