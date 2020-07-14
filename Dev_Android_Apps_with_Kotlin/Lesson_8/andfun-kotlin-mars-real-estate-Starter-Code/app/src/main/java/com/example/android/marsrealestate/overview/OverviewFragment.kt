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
import com.example.android.marsrealestate.overview.adapter.ImageAdapter
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.grid_view_item.*

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()
    private val imageAdapter: ImageAdapter = ImageAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_overview, container, false)
        setObservers()
        setHasOptionsMenu(true)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //устанавливаем базовые значения для views здесь
        setResponse("Base url")
        //setPicture()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setObservers() {
        Log.i("SetObservers", "Setted")
        viewModel.apply {
            status.observe(viewLifecycleOwner, Observer { response ->
                setResponse(response)
            })
            property.observe(viewLifecycleOwner, Observer { property ->
                setUrlToImgText(property.imgSrcUrl)
                Log.i("SrcURLIntoObserver", "${property.imgSrcUrl}")
                //setPicture()
            })
        }
    }

    private fun setResponse(response: String){
        txtvResponse.text = response
    }

    private fun setPicture() {
        val imgSrcUrl = viewModel.property.value?.imgSrcUrl
        Log.i("SrcURLIntoObserver", "${imgSrcUrl}")
        imageAdapter.bindImage(imgvMars, imgSrcUrl)
    }

    private fun setUrlToImgText(url: String){
        txtvResponse.text = url
    }
}
