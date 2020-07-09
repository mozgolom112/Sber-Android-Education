/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.ScoreFragmentBinding
import kotlinx.android.synthetic.main.game_fragment.*
import kotlinx.android.synthetic.main.game_fragment.TextScore
import kotlinx.android.synthetic.main.score_fragment.*

/**
 * Fragment where the final score is shown, after the game is over
 */
class ScoreFragment : Fragment() {

    private val viewModelFactory: ScoreViewModelFactory by lazy {
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        ScoreViewModelFactory(scoreFragmentArgs.score)
    }

    private val viewModel: ScoreViewModel by viewModels { viewModelFactory }

    private lateinit var binding: ScoreFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class.
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.score_fragment,
                container,
                false
        )
        setDataBinding()
        setObservers()
        return binding.root
    }
    private fun setDataBinding() {
        binding.apply {
            scoreViewModel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun setObservers(){
       viewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer { hasPressedPlayAgain ->
           if (hasPressedPlayAgain) {
                onPlayAgain()
            }
        })
    }

    private fun onPlayAgain() {
        findNavController().navigate(ScoreFragmentDirections.actionRestart())
    }
}
