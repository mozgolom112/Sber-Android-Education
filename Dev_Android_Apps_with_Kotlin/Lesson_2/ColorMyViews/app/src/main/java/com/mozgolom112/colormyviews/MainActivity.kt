package com.mozgolom112.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners(){
        val clickableViews: List<View> =
            listOf(TextBoxOne, TextBoxTwo, TextBoxThree,
                TextBoxFour, TextBoxFive, constraint_layout,
                BtnRed, BtnGreen, BtnYellow)
        clickableViews.forEach{
            it.setOnClickListener { makeColored(it) }
        }
    }
    
    private fun makeColored(view: View){
        when (view.id){
            R.id.TextBoxOne -> view.setBackgroundColor(Color.BLACK)
            R.id.TextBoxTwo -> view.setBackgroundColor(Color.DKGRAY)

            R.id.TextBoxThree -> view.setBackgroundColor(Color.CYAN)
            R.id.TextBoxFour -> view.setBackgroundColor(Color.BLUE)
            R.id.TextBoxFive -> view.setBackgroundColor(Color.YELLOW)

            R.id.BtnRed -> TextBoxThree.setBackgroundColor( getResources().getColor(R.color.my_red) )
            R.id.BtnYellow -> TextBoxFour.setBackgroundColor(getResources().getColor(R.color.my_yellow) )
            R.id.BtnGreen -> TextBoxFive.setBackgroundColor(R.color.my_green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}