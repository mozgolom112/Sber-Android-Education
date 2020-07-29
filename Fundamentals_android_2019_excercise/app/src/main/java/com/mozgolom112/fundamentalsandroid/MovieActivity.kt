package com.mozgolom112.fundamentalsandroid

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.mozgolom112.fundamentalsandroid.adapters.DetailsPagerAdapter
import com.mozgolom112.fundamentalsandroid.support.DataUtil
import kotlinx.android.synthetic.main.fragment_gallery_details.*

class MovieActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery_details)

        val pagerAdapter = DetailsPagerAdapter(supportFragmentManager, DataUtil.generateMovies())
        vpPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        val currentItem = vpPager.currentItem
        val isBegin = currentItem == 0

        if (isBegin) super.onBackPressed() else vpPager.currentItem = currentItem - 1
    }
}