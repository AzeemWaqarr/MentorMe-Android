package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Noti : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti)

        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)

        clickableIV.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
    }
}