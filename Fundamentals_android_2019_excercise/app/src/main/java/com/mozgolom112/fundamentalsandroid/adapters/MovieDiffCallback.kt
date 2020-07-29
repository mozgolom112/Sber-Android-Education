package com.mozgolom112.fundamentalsandroid.adapters

import androidx.recyclerview.widget.DiffUtil
import com.mozgolom112.fundamentalsandroid.models.Movie

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}