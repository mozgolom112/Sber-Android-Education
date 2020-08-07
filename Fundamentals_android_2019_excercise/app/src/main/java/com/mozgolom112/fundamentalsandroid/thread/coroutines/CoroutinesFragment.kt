package com.mozgolom112.fundamentalsandroid.thread.coroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.thread.CounterCoroutinesTaskViewModel
import kotlinx.android.synthetic.main.fragment_thread.*


class CoroutinesFragment : Fragment(R.layout.fragment_thread) {

    private val counterCoroutinesTask: CounterCoroutinesTaskViewModel by viewModels<CounterCoroutinesTaskViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListeners()
        setObservers()
    }

    private fun setOnClickListeners() {
        btnCreate.setOnClickListener {
            counterCoroutinesTask.onCreateClick()
        }
        btnStart.setOnClickListener {
            counterCoroutinesTask.onStartClick()
        }
        btnCancel.setOnClickListener {
            counterCoroutinesTask.onCancelClick()
        }
    }

    private fun setObservers() {
        counterCoroutinesTask.apply {
            count.observe(viewLifecycleOwner, Observer { count ->
                txtvTextForThreads.text = count.toString()
            })
            isDone.observe(viewLifecycleOwner, Observer { isDone ->
                if (isDone) {
                    txtvTextForThreads.text = "Done!"
                    isDoneWasCalled()
                }
            })
            isJobNotBeCreatedError.observe(viewLifecycleOwner, Observer { isError ->
                if (isError) {
                    txtvTextForThreads.text =
                        "Job must be created. Please press 'Create' before start"
                    isJobNotBeCreatedErrorWasCalled()
                }
            })
            isCoroutineCancelledError.observe(viewLifecycleOwner, Observer { isError ->
                if (isError) {
                    txtvTextForThreads.text =
                        "Coroutine has already canceled. Please press 'Create' for recreate coroutine with job"
                    isCoroutineCancelledErrorWasCalled()
                }
            })
            isCoroutineExistError.observe(viewLifecycleOwner, Observer { isError ->
                if (isError) {
                    txtvTextForThreads.text =
                        "Coroutine is not exist. Please press 'Create' for creating coroutine with job"
                    isCoroutineExistErrorWasCalled()
                }
            })
        }
    }

}