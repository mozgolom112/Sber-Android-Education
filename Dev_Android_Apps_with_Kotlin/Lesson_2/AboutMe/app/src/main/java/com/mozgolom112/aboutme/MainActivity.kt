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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val myName: MyName = MyName("Nikita Golovanov")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName
        //findViewById<Button>(R.id.done_button).setOnClickListener {
        //    addNickname(it)
        //}
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
    }
    //в нашем случае будет кнопка, т.е. view - кнопка view which the func was called
    private fun addNickname(view: View) {

        /*
        binding.nicknameEdit.text = binding.nicknameEdit.text
        binding.nicknameEdit.visibility = View.GONE
        binding.doneButton.visibility = View.GONE //скрываем кнопку
        binding.nicknameText.visibility = View.VISIBLE

         */
        binding.apply {
            //вопрос, можно ли убрать binding. после знака равно?
            //nicknameText.text = nicknameEdit.text
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE //скрываем кнопку
            nicknameText.visibility = View.VISIBLE
        }

        //Hide the keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}