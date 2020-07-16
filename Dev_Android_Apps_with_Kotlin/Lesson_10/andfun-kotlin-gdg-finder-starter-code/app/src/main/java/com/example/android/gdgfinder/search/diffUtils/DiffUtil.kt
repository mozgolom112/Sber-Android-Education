package com.example.android.gdgfinder.search.diffUtils

import androidx.recyclerview.widget.DiffUtil
import com.example.android.gdgfinder.network.GdgChapter

object DiffCallback : DiffUtil.ItemCallback<GdgChapter>() {
    override fun areItemsTheSame(oldItem: GdgChapter, newItem: GdgChapter) = oldItem === newItem

    override fun areContentsTheSame(oldItem: GdgChapter, newItem: GdgChapter) = oldItem == newItem
}