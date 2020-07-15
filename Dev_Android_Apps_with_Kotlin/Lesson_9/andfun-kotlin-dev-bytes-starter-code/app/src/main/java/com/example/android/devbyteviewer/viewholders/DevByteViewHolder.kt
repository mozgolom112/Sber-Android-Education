package com.example.android.devbyteviewer.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.android.devbyteviewer.R
import com.example.android.devbyteviewer.domain.Video
import com.example.android.devbyteviewer.util.setImageUrl

class DevByteViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
    private val titleText: TextView = view.findViewById(R.id.txtvTitle)
    private val shortDescriptionText: TextView = view.findViewById(R.id.txtvDescription)
    private val videoThumbnail: ImageView = view.findViewById(R.id.imgvVideoThumbnail)
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.devbyte_item
    }
    fun bind(inputVideo: Video) = inputVideo.apply {
            titleText.text = title
            shortDescriptionText.text = shortDescription
            videoThumbnail.setImageUrl(thumbnail)
        }

}
