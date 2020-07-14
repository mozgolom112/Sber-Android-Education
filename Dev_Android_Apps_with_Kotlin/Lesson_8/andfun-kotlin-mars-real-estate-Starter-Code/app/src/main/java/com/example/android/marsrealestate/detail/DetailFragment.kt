/*
 *  Copyright 2018, The Android Open Source Project
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.example.android.marsrealestate.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.bindImage
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private val viewModel by lazy { initViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_detail, container, false)
        setObservers()
        return viewRoot
    }

    private fun initViewModel(): DetailViewModel {
        val application = requireNotNull(requireActivity()).application
        val marsProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = DetailViewModelFactory(marsProperty, application)
        val viewModel: DetailViewModel by viewModels { viewModelFactory }
        return viewModel
    }

    private fun setObservers() {
        viewModel.apply {
            selectedProperty.observe(viewLifecycleOwner, Observer { marsProperty ->
                setDetails(marsProperty)
            })
        }
    }

    private fun setDetails(marsProperty: MarsProperty) {
        imgvMainPhoto.bindImage(marsProperty.imgSrcUrl)
        txtvPropertyType.text = viewModel.displayPropertyType()
        txtvPriceValue.text = viewModel.displayPropertyPrice()
    }

}