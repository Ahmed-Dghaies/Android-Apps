package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

/**
 * Main Activity class
 * @property binding used to bind object of the view
 * @property myName used to store the name and nickname of the user of the app 
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myName: MyName = MyName("Ahmed Dghaies")

    /**
     * OnCreate method is where the application is initialized it will call the onCreate method from its superclass
     * initialize the components of the app to be used instead of looking for them each time we need to use those views
     * @param savedInstanceState if we save the state of the application we can give it back to the onCreate method
     *  so that we don't lose that specific state*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.myName = myName

        binding.doneButton.setOnClickListener{
            addNickname(it)
        }
    }


    /** Called when the user taps the Done button
     * it fills the textView with the typed text value and make it visibile and then
     * hide both the button and the edittext view
     * @param view object that we clicked on, which is the button in this case
     */
    private fun addNickname(view: View) {
        val editText= findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView= findViewById<TextView>(R.id.nickname_textView)

        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility= View.GONE
            view.visibility=View.GONE
            nicknameTextView.visibility= View.VISIBLE
        }


        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}
