package com.example.android.gdgfinder.search.viewholder

import android.location.Location
import com.example.android.gdgfinder.network.GdgChapter
import com.example.android.gdgfinder.network.GdgResponse
import com.example.android.gdgfinder.network.LatLong
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Holds data sorted by the distance from the last location.
 *
 * Note, by convention this class won't sort on the Main thread. This is not a public API and should
 * only be called by [doSortData].
 */
class SortedData private constructor(
    val chapters: List<GdgChapter>,
    val filters: List<String>,
    val chaptersByRegion: Map<String, List<GdgChapter>>
) {

    companion object {
        /**
         * Sort the data from a [GdgResponse] by the specified location.
         *
         * @param response the response to sort
         * @param location the location to sort by, if null the data will not be sorted.
         */
        suspend fun from(response: GdgResponse, location: Location?): SortedData {
            return withContext(Dispatchers.Default) {
                // this sorting is too expensive to do on the main thread, so do thread confinement here.
                val chapters: List<GdgChapter> = response.chapters.sortByDistanceFrom(location)
                // use distinctBy which will maintain the input order - this will have the effect of making
                // a filter list sorted by the distance from the current location
                val filters: List<String> = chapters.map { it.region }.distinctBy { it }
                // group the chapters by region so that filter queries don't require any work
                val chaptersByRegion: Map<String, List<GdgChapter>> =
                    chapters.groupBy { it.region }
                // return the sorted result
                SortedData(chapters, filters, chaptersByRegion)
            }

        }


        /**
         * Sort a list of GdgChapter by their distance from the specified location.
         *
         * @param currentLocation returned list will be sorted by the distance, or unsorted if null
         */
        private fun List<GdgChapter>.sortByDistanceFrom(currentLocation: Location?): List<GdgChapter> {
            currentLocation ?: return this

            return sortedBy { distanceBetween(it.geo, currentLocation) }
        }

        /**
         * Calculate the distance (in meters) between a LatLong and a Location.
         */
        private fun distanceBetween(start: LatLong, currentLocation: Location): Float {
            val results = FloatArray(3)
            Location.distanceBetween(
                start.lat,
                start.long,
                currentLocation.latitude,
                currentLocation.longitude,
                results
            )
            return results[0]
        }
    }
}