package com.example.android.devbyteviewer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.android.devbyteviewer.adapters.DiffCallback.DevByteDiffCallback
import com.example.android.devbyteviewer.domain.Video
import com.example.android.devbyteviewer.viewholders.DevByteViewHolder

class DevByteAdapter(private val clickListener: (Video) -> Unit) : ListAdapter<Video, DevByteViewHolder>(DevByteDiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevByteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val devByteView = layoutInflater.inflate(
                DevByteViewHolder.LAYOUT, parent, false)
        return DevByteViewHolder(devByteView)
    }

    override fun onBindViewHolder(holder: DevByteViewHolder, position: Int) =
            holder.bind(getItem(position), clickListener)
}
