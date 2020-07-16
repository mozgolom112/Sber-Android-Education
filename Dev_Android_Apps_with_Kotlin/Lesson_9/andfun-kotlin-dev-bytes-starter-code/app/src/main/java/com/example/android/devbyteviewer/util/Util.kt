/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.devbyteviewer.util

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.android.devbyteviewer.database.DatabaseVideo
import com.example.android.devbyteviewer.domain.Video
import com.example.android.devbyteviewer.network.NetworkVideoContainer

private val PUNCTUATION = listOf(", ", "; ", ": ", " ")

/**
 * Truncate long text with a preference for word boundaries and without trailing punctuation.
 */
fun String.smartTruncate(length: Int): String {
    val words = split(" ")
    var added = 0
    var hasMore = false
    val builder = StringBuilder()
    for (word in words) {
        if (builder.length > length) {
            hasMore = true
            break
        }
        builder.append(word)
        builder.append(" ")
        added += 1
    }

    PUNCTUATION.map {
        if (builder.endsWith(it)) {
            builder.replace(builder.length - it.length, builder.length, "")
        }
    }

    if (hasMore) {
        builder.append("...")
    }
    return builder.toString()
}

/**
 * Helper method to generate YouTube app links
 */
val Video.launchUri: Uri
    get() {
        val httpUri = Uri.parse(url)
        return Uri.parse("vnd.youtube:" + httpUri.getQueryParameter("v"))
    }

fun View.goneIfNotNull(it: Any?) {
    visibility = if (it != null) View.GONE else View.VISIBLE
}

fun ImageView.setImageUrl(url: String) {
    Glide.with(context)
            .load(url)
            .into(this)
}
