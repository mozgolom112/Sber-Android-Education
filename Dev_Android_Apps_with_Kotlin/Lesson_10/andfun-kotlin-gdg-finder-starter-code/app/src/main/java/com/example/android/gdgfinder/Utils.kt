package com.example.android.gdgfinder

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.gdgfinder.network.GdgChapter
import com.example.android.gdgfinder.search.GdgListAdapter

fun RecyclerView.bindRecyclerView(data: List<GdgChapter>?) {
    val adapter = adapter as GdgListAdapter
    adapter.submitList(data) {
        // scroll the list to the top after the diffs are calculated and posted
        scrollToPosition(0)
    }
}


fun View.showOnlyWhenEmpty(data: List<GdgChapter>?) {
    visibility = when {
        data == null || data.isEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}