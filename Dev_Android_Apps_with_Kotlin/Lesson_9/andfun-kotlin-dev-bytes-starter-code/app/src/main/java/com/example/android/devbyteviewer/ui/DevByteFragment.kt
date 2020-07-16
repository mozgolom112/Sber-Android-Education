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

package com.example.android.devbyteviewer.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.devbyteviewer.R
import com.example.android.devbyteviewer.adapters.DevByteAdapter
import com.example.android.devbyteviewer.domain.Video
import com.example.android.devbyteviewer.util.goneIfNotNull
import com.example.android.devbyteviewer.util.launchUri
import com.example.android.devbyteviewer.viewmodels.DevByteViewModel
import kotlinx.android.synthetic.main.fragment_dev_byte.*

class DevByteFragment : Fragment() {

    private val viewModel: DevByteViewModel by lazy { initDevByteViewModel() }

    private var viewModelAdapter: DevByteAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val viewRoot = inflater.inflate(
                R.layout.fragment_dev_byte, container, false)

        viewModelAdapter = DevByteAdapter { makeYoutubeIntent(it) }

        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObservers()
        recyclervDevByte.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }
    }

    private fun initDevByteViewModel(): DevByteViewModel {
        val application = requireActivity().application
        val viewModelFactory = DevByteViewModel.Factory(application)
        val viewModel: DevByteViewModel by viewModels { viewModelFactory }
        return viewModel
    }

    private fun makeYoutubeIntent(it: Video) {
        val packageManager = context?.packageManager ?: return

        var intent = Intent(Intent.ACTION_VIEW, it.launchUri)
        if (intent.resolveActivity(packageManager) == null) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
        }
        startActivity(intent)
    }

    private fun setObservers() {
        viewModel.apply {
            playlist.observe(viewLifecycleOwner, Observer<List<Video>> { videos ->
                progbLoadingSpinner.goneIfNotNull(videos)
                videos?.apply {
                    viewModelAdapter?.submitList(videos)
                }
            })
        }
    }
}

