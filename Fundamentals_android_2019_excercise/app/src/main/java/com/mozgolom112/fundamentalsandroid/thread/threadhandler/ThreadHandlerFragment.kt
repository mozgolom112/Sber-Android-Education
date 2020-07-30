package com.mozgolom112.fundamentalsandroid.thread.threadhandler

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mozgolom112.fundamentalsandroid.R
import com.mozgolom112.fundamentalsandroid.thread.SimpleAsyncTask
import kotlinx.android.synthetic.main.fragment_thread.*

class ThreadHandlerFragment : Fragment(R.layout.fragment_thread){

    val simpleAsyncTask: SimpleAsyncTask by viewModels()
    //For example
    //private var simpleAsyncTask: SimpleAsyncTask? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        btnCreate.setOnClickListener {
            simpleAsyncTask.isCancelled.value = false
            setObservers() //add observers
        }
        btnStart.setOnClickListener {
            val taskCopy = simpleAsyncTask

            if (taskCopy == null || taskCopy.isCancelled.value == true) {
                Toast.makeText(requireContext(), "msg_should_create_task", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),"msg_thread_onstart", Toast.LENGTH_SHORT).show()
                simpleAsyncTask?.execute()
            }

        }
        btnCancel.setOnClickListener {
            if (simpleAsyncTask == null) {
                Toast.makeText(requireContext(), "msg_should_create_task", Toast.LENGTH_SHORT).show()
            } else {
                simpleAsyncTask?.cancel()
            }
        }
    }

    private fun setObservers() {
        simpleAsyncTask?.apply {

            progress.observe(viewLifecycleOwner, Observer { progress ->
                txtvTextForThreads.text = progress.toString()
            })

            isOnPreExecutorCalled.observe(viewLifecycleOwner, Observer {isOnPreExecutorCalled ->
                if (isOnPreExecutorCalled){
                    Toast.makeText(requireContext(), "msg_PREexecute", Toast.LENGTH_SHORT)
                        .show()
                    txtvTextForThreads.text = "Task was created"
                    isOnPreExecutorWasCalled() //change flag on false
                }
            })

            isOnPostExecutorCalled.observe(viewLifecycleOwner, Observer {isOnPostExecutorCalled ->
                if (isOnPostExecutorCalled){
                    Toast.makeText(requireContext(), "msg_POSTexecute", Toast.LENGTH_SHORT)
                        .show()

                    txtvTextForThreads.text = "Done!"
                    isOnPostExecutorWasCalled()
                    //simpleAsyncTask = null
                }

            })
        }
    }
}