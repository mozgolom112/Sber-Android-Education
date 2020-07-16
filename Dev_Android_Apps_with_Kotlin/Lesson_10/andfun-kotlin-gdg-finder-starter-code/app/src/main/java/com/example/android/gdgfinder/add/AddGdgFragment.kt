package com.example.android.gdgfinder.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.example.android.gdgfinder.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.add_gdg_fragment.*

class AddGdgFragment : Fragment() {

    private val viewModel: AddGdgViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.add_gdg_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setObserves()
        setClickListeners()
    }

    private fun setObserves() {
        viewModel.apply {
            showSnackbarEvent.observe(viewLifecycleOwner, Observer {
                it?.let {
                    showSnackbar()
                }
            })
        }
    }

    private fun showSnackbar() {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            getString(R.string.application_submitted),
            Snackbar.LENGTH_SHORT
        ).show()
        viewModel.doneShowingSnackbar()
    }

    private fun setClickListeners() {
        btnEditTextWhy.setOnClickListener {viewModel.onSubmitApplication()}
    }
}
