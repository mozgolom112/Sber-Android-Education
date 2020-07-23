package com.mozgolom112.fundamentalsandroid.actor

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.mozgolom112.fundamentalsandroid.R
import kotlinx.android.synthetic.main.list_item_actor.view.*

class ActorListAdapter(context: Context, var actors: List<Actor>) : BaseAdapter(){
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItem(position: Int): Actor = actors[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = actors.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val item = inflater.inflate(R.layout.list_item_actor, parent, false)

        val actor = actors[position]

        item.apply {
            setImage(actor.avatar, item)
            txtvName.text = actor.name
            imgvOscar.visibility = if (actor.hasOscar) View.VISIBLE else View.GONE
        }

        return item
    }

    private fun setImage(avatar: String, item: View) {
        item.imgvAvatar.setImageURI(Uri.parse(avatar))
    }
}