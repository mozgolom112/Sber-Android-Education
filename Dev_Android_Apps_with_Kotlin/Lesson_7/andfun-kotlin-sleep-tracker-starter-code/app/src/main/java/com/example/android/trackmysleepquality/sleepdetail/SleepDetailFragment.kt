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

package com.example.android.trackmysleepquality.sleepdetail

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
import com.example.android.trackmysleepquality.databinding.FragmentSleepDetailBinding


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SleepDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SleepDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SleepDetailFragment : Fragment() {

    private val sleepDetailViewModel by lazy { initSleepDetailViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentSleepDetailBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_sleep_detail, container, false)
        val sleepDetailViewModel = initSleepDetailViewModel()
        fulfillBinding(binding)

        setObservers()

        return binding.root
    }

    private fun initSleepDetailViewModel(): SleepDetailViewModel {
        val application = requireNotNull(activity).application
        val arguments = SleepDetailFragmentArgs.fromBundle(requireArguments())
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepDetailViewModelFactory(arguments.sleepNightKey, dataSource)
        val sleepDetailViewModel: SleepDetailViewModel by viewModels { viewModelFactory }
        return sleepDetailViewModel
    }

    private fun fulfillBinding(binding: FragmentSleepDetailBinding) {
        binding.apply {
            this.sleepDetailViewModel = sleepDetailViewModel
            setLifecycleOwner(viewLifecycleOwner)
        }
    }


    private fun setObservers() {
        sleepDetailViewModel.apply {
            navigateToSleepTracker.observe(viewLifecycleOwner, Observer { hasNavigated ->
                if (hasNavigated == true) {
                    navigateToSleepTracker()
                    sleepDetailViewModel.doneNavigating()
                }
            })
        }
    }

    private fun navigateToSleepTracker() {
        findNavController().navigate(
                SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
    }
}
