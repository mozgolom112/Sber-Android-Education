package com.mozgolom112.fundamentalsandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.dependency.Dependencies
import com.mozgolom112.fundamentalsandroid.services.ServiceDelegate
import com.mozgolom112.fundamentalsandroid.viewmodels.BackgroundServiceViewModel
import com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory.BackgroundServiceViewModelFactory
import com.mozgolom112.fundamentalsandroid.viewmodels.viewmodelsfactory.MovieViewModelFactory
import kotlinx.android.synthetic.main.fragment_background_service.*

class BackgroundServiceFragment : Fragment(R.layout.fragment_background_service) {

    private val serviceDelegate: ServiceDelegate by lazy {Dependencies.serviceDelegate}

    private val viewModelFactory by lazy { BackgroundServiceViewModelFactory(Dependencies.heavyWorkManager) }
    private val viewModel: BackgroundServiceViewModel by viewModels{viewModelFactory}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        setObservers()
    }

    private fun setOnClickListeners() {
        btnStartIntentService.setOnClickListener {

        }

        btnStartService.setOnClickListener {
            activity?.run { serviceDelegate.startDownloadService(this, true) }
        }
    }


    private fun setObservers() {
        viewModel.apply {
            progressStatus.observe(viewLifecycleOwner, Observer {
                Log.d("progressStatus", "Current progress status: ${it}")
                updateProgress(it)
            })
        }
    }

    private fun updateProgress(it: Int?) {
        txtvServiceProgressBar.text = it.toString() + "%"
    }
}