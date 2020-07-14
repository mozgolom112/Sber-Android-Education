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

package com.example.android.marsrealestate.overview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsApiFilter
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MarsApiStatus {LOADING, ERROR, DONE}

class OverviewViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val properties = MutableLiveData<List<MarsProperty>>()
    val status = MutableLiveData<MarsApiStatus>()
    val navigateToSelectedProperty = MutableLiveData<MarsProperty>()

    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        uiScope.launch {
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties(filter.value)
            try {
                status.value = MarsApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                status.value = MarsApiStatus.DONE
                if (listResult.isNotEmpty()){
                    properties.value = listResult
                }
            } catch (e: Exception) {
                status.value = MarsApiStatus.ERROR
                properties.value = ArrayList()
            }
        }
    }

    fun displayPropertyDetails(marsProperty: MarsProperty) {
        navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        navigateToSelectedProperty.value = null
    }

    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }
}
