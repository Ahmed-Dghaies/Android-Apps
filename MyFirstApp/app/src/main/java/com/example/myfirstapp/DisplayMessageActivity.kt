package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {

    /**
     * OnCreate method is where the application is initialized it will call the onCreate method from its superclass
     * initialize the components of the app to be used instead of looking for them each time we need to use those views
     * @param savedInstanceState if we save the state of the application we can give it back to the onCreate method
     *  so that we don't lose that specific state*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }
    }
}
