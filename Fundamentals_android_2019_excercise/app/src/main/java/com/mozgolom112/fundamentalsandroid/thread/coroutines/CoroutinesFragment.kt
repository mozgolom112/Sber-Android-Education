package com.mozgolom112.fundamentalsandroid.thread.coroutines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.thread.CounterCoroutinesTask
import kotlinx.android.synthetic.main.fragment_thread.*


class CoroutinesFragment : Fragment(R.layout.fragment_thread) {
    private val counterCoroutinesTask by lazy { CounterCoroutinesTask() }

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
            isDone.observe(viewLifecycleOwner, Observer {isDone ->
                if (isDone) txtvTextForThreads.text = "Done!"
            })
        }
    }

}