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
 */

package com.example.android.trackmysleepquality.sleepquality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.FragmentSleepQualityBinding


class SleepQualityFragment : Fragment() {

    private val sleepQualityViewModel by lazy { initViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = initBinding(inflater, container).apply { fulfillBinding(this) }

        setObservers()

        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?)
            : FragmentSleepQualityBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_quality, container, false)

    private fun initViewModel(): SleepQualityViewModel {

        val application = requireNotNull(this.activity).application
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)
        val viewModel: SleepQualityViewModel by viewModels {viewModelFactory}

        return  viewModel
    }

    private fun fulfillBinding(binding: FragmentSleepQualityBinding){
        val sleepQualityViewModel = sleepQualityViewModel
        binding.apply {
            this.sleepQualityViewModel = sleepQualityViewModel
        }
    }

    private fun setObservers(){
        sleepQualityViewModel.apply {
            navigateToSleepTracker.observe(viewLifecycleOwner, Observer { hasNavigate ->
                if (hasNavigate == true){
                    navigateToSleepTracker()
                }
            })
        }
    }

    private fun navigateToSleepTracker(){
        this.findNavController().navigate(
                SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
        sleepQualityViewModel.doneNavigating()
    }
}
