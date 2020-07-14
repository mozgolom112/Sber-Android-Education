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

package com.example.android.marsrealestate.overview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.android.marsrealestate.R
import com.example.android.marsrealestate.network.MarsProperty
import com.example.android.marsrealestate.overview.diffUtils.DiffCallback
import com.example.android.marsrealestate.overview.viewHolders.MarsPropertyViewHolder
import kotlinx.android.synthetic.main.grid_view_item.view.*
import java.util.zip.Inflater

class PhotoGridAdapter: ListAdapter<MarsProperty, MarsPropertyViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.grid_view_item, parent, false)
        return MarsPropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
         holder.bind(getItem(position))
    }
}