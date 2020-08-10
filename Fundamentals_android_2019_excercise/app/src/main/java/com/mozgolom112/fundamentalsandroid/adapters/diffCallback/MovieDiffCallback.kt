package com.mozgolom112.fundamentalsandroid.adapters.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.mozgolom112.fundamentalsandroid.models.MovieModel

object MovieDiffCallback : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean = oldItem == newItem
}