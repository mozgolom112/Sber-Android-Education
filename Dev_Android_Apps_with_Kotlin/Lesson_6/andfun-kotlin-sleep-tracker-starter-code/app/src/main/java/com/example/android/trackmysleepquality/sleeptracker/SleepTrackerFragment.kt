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

package com.example.android.trackmysleepquality.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.PrimaryKey
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.FragmentSleepTrackerBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_sleep_tracker.*


class SleepTrackerFragment : Fragment() {

    private val sleepTrackerViewModel by lazy { initSleepViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO("Remove data binding, if it's possible")
        val binding = initBinding(inflater, container)

        fulfillBinding(binding)

        setObservers()

        return binding.root
    }

    private fun initSleepViewModel(): SleepTrackerViewModel {

        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
        val sleepTrackerViewModel: SleepTrackerViewModel by viewModels { viewModelFactory }
        return  sleepTrackerViewModel
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?)
            : FragmentSleepTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_tracker, container, false)

    private fun fulfillBinding(binding: FragmentSleepTrackerBinding){
        //TODO("Remove this variable")
        val sleepTrackerViewModel = sleepTrackerViewModel
        binding.apply {
            this.sleepTrackerViewModel = sleepTrackerViewModel
            setLifecycleOwner(viewLifecycleOwner)
        }
    }

    private fun setObservers(){
        sleepTrackerViewModel.apply {
            navigateToSleepQuality.observe(viewLifecycleOwner, Observer {
                night -> navigateToSleepQuality(night)
            })
            showSnackbarEvent.observe(viewLifecycleOwner, Observer { hasShowed ->
                showSnackbar(hasShowed)
            })
        }

    }
    
    private fun navigateToSleepQuality(night: SleepNight?){
        night?.let {
            this.findNavController().navigate(
                    SleepTrackerFragmentDirections
                            .actionSleepTrackerFragmentToSleepQualityFragment(night.nightId))
            sleepTrackerViewModel.doneNavigation()
        }
    }

    private fun showSnackbar(hasShowed: Boolean){
        if (hasShowed) {
            Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
            ).show()
            sleepTrackerViewModel.doneSnackBarEvent()
        }
    }
}
