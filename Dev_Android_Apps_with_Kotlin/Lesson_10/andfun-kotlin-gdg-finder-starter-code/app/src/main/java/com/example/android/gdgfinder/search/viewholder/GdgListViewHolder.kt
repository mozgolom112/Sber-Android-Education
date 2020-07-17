package com.example.android.gdgfinder.search.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.android.gdgfinder.R
import com.example.android.gdgfinder.network.GdgChapter


class GdgListViewHolder(private var view: View) :
    RecyclerView.ViewHolder(view) {

    private val constrainLayout: ConstraintLayout = view.findViewById(R.id.constlItem)
    private val chapterName: TextView = view.findViewById(R.id.txtvChapterName)

    fun bind(listener: (chapter: GdgChapter) -> Unit, gdgChapter: GdgChapter) {
        chapterName.text = gdgChapter.name
        constrainLayout.setOnClickListener { listener }
    }

    companion object {
        fun from(parent: ViewGroup): GdgListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.list_item, parent, false)
            return GdgListViewHolder(view)
        }
    }
}