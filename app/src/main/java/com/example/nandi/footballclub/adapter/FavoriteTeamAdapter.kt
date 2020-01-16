package com.example.nandi.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.model.FavoriteTeam
import com.squareup.picasso.Picasso
import org.jetbrains.anko.sdk25.coroutines.onClick

class FavoriteTeamAdapter(private val favoriteTeam: List<FavoriteTeam>, private val listener: (FavoriteTeam) -> Unit)
    : RecyclerView.Adapter<FavoriteTeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_team, parent, false)
        return FavoriteTeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favoriteTeam.size
    }

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bind(favoriteTeam[position], listener)
    }
}

class FavoriteTeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val teamBadge: ImageView = view.findViewById(R.id.team_badge)
    private val teamName: TextView = view.findViewById(R.id.team_name)

    fun bind(favoriteTeam: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
        Picasso.get().load(favoriteTeam.teamBadge).into(teamBadge)

        teamName.text = favoriteTeam.teamName
        itemView.onClick { listener(favoriteTeam) }

    }

}
