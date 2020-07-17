package com.example.android.gdgfinder.search

import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.android.gdgfinder.network.GdgApi
import com.example.android.gdgfinder.network.GdgChapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception


class GdgListViewModel : ViewModel() {

    private val repository = GdgChapterRepository(GdgApi.retrofitService)

    private var filter = FilterHolder()

    private var Job: Job? = null

    val gdgList = MutableLiveData<List<GdgChapter>>()
    val regionList = MutableLiveData<List<String>>()
    val showNeedLocation = MutableLiveData<Boolean>()
    val exceptionError = MutableLiveData<Exception>()

    init {
        // process the initial filter
        onQueryChanged()

        viewModelScope.launch {
            delay(5_000)
            showNeedLocation.value = !repository.isFullyInitialized
        }
    }

    private fun onQueryChanged() {
        Job?.cancel() // if a previous query is running cancel it before starting another
        Job = viewModelScope.launch {
            try {
                // this will run on a thread managed by Retrofit
                gdgList.value = repository.getChaptersForFilter(filter.currentValue)
                Log.i("onQueryChanged", gdgList.value.toString())
                repository.getFilters().let {
                    // only update the filters list if it's changed since the last time
                    if (it != regionList.value) {
                        regionList.value = it
                    }
                }
            } catch (e: IOException) {
                gdgList.value = listOf()
                exceptionError.value = e
            }
        }
    }

    fun onLocationUpdated(location: Location) {
        viewModelScope.launch {
            try {
                repository.onLocationChanged(location)
            } catch (exp: HttpException) {
                exceptionError.value = exp
            }
            onQueryChanged()
        }
    }

    fun onFilterChanged(filter: String, isChecked: Boolean) {
        if (this.filter.update(filter, isChecked)) {
            onQueryChanged()
        }
    }

    private class FilterHolder {
        var currentValue: String? = null
            private set

        fun update(changedFilter: String, isChecked: Boolean): Boolean {
            if (isChecked) {
                currentValue = changedFilter
                return true
            } else if (currentValue == changedFilter) {
                currentValue = null
                return true
            }
            return false
        }
    }
}

