package com.mozgolom112.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.mozgolom112.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding
            by lazy { DataBindingUtil.setContentView<ActivityMainBinding>(
                this, R.layout.activity_main)  }

    private val myName: MyName = MyName("Nikita Golovanov")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            myName = myName
            BtnDone.setOnClickListener {
                    addNickname(it)
                }
        }
    }
    private fun addNickname(view: View) {

        binding.apply {
            myName?.nickname = EditTextNickname.text.toString()
            invalidateAll()
            EditTextNickname.visibility = View.GONE
            BtnDone.visibility = View.GONE
            TextNickname.visibility = View.VISIBLE
        }

        hideKeyboard(view)
    }

    private fun hideKeyboard(view: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}