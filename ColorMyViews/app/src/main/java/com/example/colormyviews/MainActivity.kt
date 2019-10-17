package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main Activity class
 */
class MainActivity : AppCompatActivity() {

    /**
    * OnCreate method is where the application is initialized it will call the onCreate method from its superclass
    * initialize the components of the app to be used instead of looking for them each time we need to use those views
    * @param savedInstanceState if we save the state of the application we can give it back to the onCreate method
    *  so that we don't lose that specific state*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    /**
     * this methods sets the on click listeners for all the text views and buttond
     */
    private fun setListeners() {
        var clickableViews: List<View> =
            listOf(box_one_text, box_two_text, text_box_three, text_box_four, text_box_five, red_button, green_button, blue_button)

        for (item in clickableViews) {
            item.setOnClickListener{ makeColored(it)}
        }
    }

    /**
     * this function will set the color for each box we tap on
     * @param view object that we clicked on, which is the text areas in this case
     */
    private fun makeColored(view: View) {
        when (view.id) {

            // Boxes using Color class colors for background
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)

            // Boxes using custom colors for background
            R.id.red_button -> text_box_three.setBackgroundResource(R.color.my_red)
            R.id.blue_button -> text_box_four.setBackgroundResource(R.color.my_blue)
            R.id.green_button -> text_box_five.setBackgroundResource(R.color.my_green)

            // Boxes using Android color resources for background
            R.id.text_box_three -> view.setBackgroundColor(
                ContextCompat.getColor(this, android.R.color.holo_green_light))
            R.id.text_box_four -> view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
            R.id.text_box_five -> view.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_light))

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
