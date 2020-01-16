package com.example.nandi.footballclub.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.nandi.footballclub.R
import com.example.nandi.footballclub.model.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerAdapter(private val players: MutableList<Player>, private val listener: (Player) -> Unit)
    : RecyclerView.Adapter<PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position], listener)
    }
}

class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val imgPlayer: ImageView = view.findViewById(R.id.img_player)
    private val txtPlayer: TextView = view.findViewById(R.id.txt_player)
    private val txtPosition: TextView = view.findViewById(R.id.txt_position)

    fun bind(player: Player, listener: (Player) -> Unit){

        Picasso.get().load(player.strCutout).into(imgPlayer)
        txtPlayer.text = player.strPlayer
        txtPosition.text = player.strPosition

        itemView.onClick { listener(player) }


    }

}