package com.mozgolom112.fundamentalsandroid.actor

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mozgolom112.fundamentalsandroid.R
import kotlinx.android.synthetic.main.list_item_actor.view.*

class ActorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val avatar: ImageView = itemView.findViewById(R.id.imgvAvatar)
    private val name: TextView = itemView.findViewById(R.id.txtvName)
    private val oscar: ImageView = itemView.findViewById(R.id.imgvOscar)

    fun bind(actor: Actor){
        setImage(actor.avatar, avatar)
        name.text = actor.name
        oscar.visibility = if (actor.hasOscar) View.VISIBLE else View.GONE
    }

    private fun setImage(avatar: String, view: ImageView) {
        view.imgvAvatar.setImageURI(Uri.parse(avatar))
    }
}