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

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.bindRecyclerView
import com.example.android.marsrealestate.overview.adapters.PhotoGridAdapter
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.grid_view_item.*

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_overview, container, false)
        setObservers()
        setHasOptionsMenu(true)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setObservers() {
        Log.i("SetObservers", "Setted")
        viewModel.apply {
            status.observe(viewLifecycleOwner, Observer { response ->
            })
            properties.observe(viewLifecycleOwner, Observer { propertyList ->
                gridvPhotos.bindRecyclerView(propertyList)
            })
        }
    }

    private fun setAdapter() {
        gridvPhotos.adapter = PhotoGridAdapter()
    }
}
