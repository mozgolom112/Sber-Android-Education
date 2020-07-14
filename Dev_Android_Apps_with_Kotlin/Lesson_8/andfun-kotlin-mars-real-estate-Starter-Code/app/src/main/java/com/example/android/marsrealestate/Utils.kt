package com.example.android.marsrealestate

import android.transition.Visibility
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.overview.MarsApiStatus
import com.example.android.marsrealestate.overview.adapters.PhotoGridAdapter

fun ImageView.bindImage (imgUrl: String?){
    imgUrl?.let {
        val imgUri= it.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(this)
    }
}

fun ImageView.bindStatus(status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.loading_animation)
        }

        MarsApiStatus.ERROR -> {
            visibility = View.VISIBLE
            setImageResource(R.drawable.ic_connection_error)
        }

        MarsApiStatus.DONE -> {
            visibility = View.GONE
        }
    }
}

fun RecyclerView.bindRecyclerView(data: List<MarsProperty>?){
    val adapter = adapter as PhotoGridAdapter
    adapter.submitList(data)
}

