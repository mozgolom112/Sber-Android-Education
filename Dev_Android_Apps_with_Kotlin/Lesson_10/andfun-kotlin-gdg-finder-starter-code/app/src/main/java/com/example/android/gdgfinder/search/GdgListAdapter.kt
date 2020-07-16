package com.example.android.gdgfinder.search

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.android.gdgfinder.network.GdgChapter
import com.example.android.gdgfinder.search.diffUtils.DiffCallback
import com.example.android.gdgfinder.search.viewholder.GdgListViewHolder

class GdgListAdapter(val clickListener: (chapter: GdgChapter) -> Unit):
    ListAdapter<GdgChapter, GdgListViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GdgListViewHolder.from(parent)

    override fun onBindViewHolder(holder: GdgListViewHolder, position: Int)  =
        holder.bind(clickListener, getItem(position))
}
