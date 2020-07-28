package com.mozgolom112.fundamentalsandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mozgolom112.fundamentalsandroid.actor.ActorRecyclerAdapter
import com.mozgolom112.fundamentalsandroid.support.DataUtil
import kotlinx.android.synthetic.main.activity_test_actors.*

class ActorListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_actors)
        setAdapters()
    }

    private fun setAdapters() {
        recycletlistvActors.apply{
            adapter = ActorRecyclerAdapter(context, DataUtil.generateActors())
        }
    }
}