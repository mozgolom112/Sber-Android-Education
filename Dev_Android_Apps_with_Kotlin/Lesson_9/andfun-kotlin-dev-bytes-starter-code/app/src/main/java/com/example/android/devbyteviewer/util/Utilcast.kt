package com.example.android.devbyteviewer.util

import com.example.android.devbyteviewer.database.DatabaseVideo
import com.example.android.devbyteviewer.domain.Video
import com.example.android.devbyteviewer.network.NetworkVideoContainer

fun List<DatabaseVideo>.asDomainModel(): List<Video> {
    return map{
        Video(
                title = it.title,
                description = it.description,
                url = it.url,
                updated = it.updated,
                thumbnail = it.thumbnail)
    }
}

/**
 * Convert Network results to domain objects
 */
fun NetworkVideoContainer.asDomainModel(): List<Video> {
    return videos.map {
        Video(
                title = it.title,
                description = it.description,
                url = it.url,
                updated = it.updated,
                thumbnail = it.thumbnail)
    }
}

/**
 * Convert Network results to database objects
 */
fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideo> {
    return videos.map {
        DatabaseVideo(
                url = it.url,
                description = it.description,
                updated = it.updated,
                title = it.title,
                thumbnail = it.thumbnail)
    }.toTypedArray()
}