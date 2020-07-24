package com.mozgolom112.fundamentalsandroid.actor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.models.Actor

class ActorRecyclerAdapter(context: Context, var actors: List<Actor>):RecyclerView.Adapter<ActorViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(inflater.inflate(R.layout.list_item_actor, parent, false))

    override fun getItemCount(): Int = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun getItem(position: Int): Actor = actors[position]
}