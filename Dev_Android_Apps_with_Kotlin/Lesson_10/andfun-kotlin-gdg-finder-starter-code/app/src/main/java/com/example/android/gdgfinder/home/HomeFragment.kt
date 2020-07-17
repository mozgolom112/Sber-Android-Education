package com.example.android.gdgfinder.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.adapters.ViewBindingAdapter.setClickListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.gdgfinder.R
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.home_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setClickListener()
    }

    private fun setObservers() {
        viewModel.apply {
            navigateToSearch.observe(viewLifecycleOwner, Observer { navigate ->
                if (navigate) {
                    findNavController().navigate(R.id.action_homeFragment_to_gdgListFragment)
                    onNavigatedToSearch()
                }
            })
        }
    }

    private fun setClickListener() {
        btnFloatingAction.setOnClickListener { viewModel.onFabClicked() }
    }
}
