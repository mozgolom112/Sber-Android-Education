package com.example.android.marsrealestate.overview.viewHolders

import android.view.View
import android.widget.GridView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.bindImage
import com.example.android.marsrealestate.network.MarsProperty

class MarsPropertyViewHolder(private var view: View) :
        RecyclerView.ViewHolder(view) {
    private val imageView : ImageView = view.findViewById(R.id.imgvMars)

    fun bind(marsProperty: MarsProperty){
        imageView.bindImage(marsProperty.imgSrcUrl)
    }
}