package com.azeemwaqar.i210679

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(Runnable { // Start the main activity
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

            // Close the splash activity
            finish()
        }, 3000) // 3000 milliseconds (3 seconds) delay

    }
}