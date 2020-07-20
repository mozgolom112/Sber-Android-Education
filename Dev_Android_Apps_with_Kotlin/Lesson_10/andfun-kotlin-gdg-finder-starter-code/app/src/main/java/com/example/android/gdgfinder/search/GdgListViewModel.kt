package com.example.android.gdgfinder.search

import android.location.Location
import androidx.lifecycle.*
import com.example.android.gdgfinder.network.GdgApi
import com.example.android.gdgfinder.network.GdgChapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException


class GdgListViewModel : ViewModel() {

    private val repository = GdgChapterRepository(GdgApi.retrofitService)

    private var filter = FilterHolder()

    private var Job: Job? = null

    val gdgList = MutableLiveData<List<GdgChapter>>()
    val regionList = MutableLiveData<List<String>>()
    val showNeedLocation = MutableLiveData<Boolean>()
    val errorHttpException = MutableLiveData<HttpException>()

    init {
        onQueryChanged()
        viewModelScope.launch {
            delay(5_000)
            showNeedLocation.value = !repository.isFullyInitialized
        }
    }

    private fun onQueryChanged() {
        Job?.cancel()
        Job = viewModelScope.launch {
            try {
                gdgList.value = repository.getChaptersForFilter(filter.currentValue)
                repository.getFilters().let {
                    if (it != regionList.value) {
                        regionList.value = it
                    }
                }
            } catch (e: Throwable) {
                when (e) {
                     is HttpException -> errorHttpException.value = e
                }
                //TODO("For testing")
                gdgList.value = listOf()
                regionList.value = listOf("Europe", "Asia", "North America", "South America")
            }
        }
    }

    fun onLocationUpdated(location: Location) {
        viewModelScope.launch {
            try {
                repository.onLocationChanged(location)
            } catch (exp: Throwable) {
                errorHttpException.value = exp as HttpException
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

