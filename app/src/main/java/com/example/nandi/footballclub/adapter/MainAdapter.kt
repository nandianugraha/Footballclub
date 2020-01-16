package com.example.nandi.footballclub.adapter

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.model.Match
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class MainAdapter(private val events: List<Match>, private val listener: (Match) -> Unit)
    : RecyclerView.Adapter<MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    override fun getItemCount(): Int = events.size

}

class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateMatch: TextView = itemView.findViewById(R.id.tv_date)
    private val timeMatch: TextView = itemView.findViewById(R.id.tv_time)
    private val homeTeam: TextView = itemView.findViewById(R.id.tv_hometeam)
    private val awayTeam: TextView = itemView.findViewById(R.id.tv_awayteam)
    private val homeScore: TextView = itemView.findViewById(R.id.tv_homescore)
    private val awayScore: TextView = itemView.findViewById(R.id.tv_awayscore)

    fun bindItem(events: Match, listener: (Match) -> Unit){

        val dateShort = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .parse(events.dateEvent)
        val dateLong = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
                .format(dateShort)

        dateMatch.text = dateLong.toString()
        timeMatch.text = events.strTime
        homeTeam.text = events.strHomeTeam
        awayTeam.text = events.strAwayTeam
        homeScore.text = events.intHomeScore ?: ""
        awayScore.text = events.intAwayScore ?: ""

        itemView.onClick { listener(events) }
    }
}

