package com.mozgolom112.fundamentalsandroid.adapters.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.mozgolom112.fundamentalsandroid.domain.Movie

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
}