package com.example.android.devbyteviewer.adapters.DiffCallback

import androidx.recyclerview.widget.DiffUtil
import com.example.android.devbyteviewer.domain.Video

object DevByteDiffCallback : DiffUtil.ItemCallback<Video>(){
    override fun areItemsTheSame(oldItem: Video, newItem: Video) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Video, newItem: Video) = oldItem.url == newItem.url
}