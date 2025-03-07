package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class NewPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pass)

        val clickableTV1: TextView = findViewById<TextView>(R.id.textView16)

        clickableTV1.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val clickableTV: TextView = findViewById<TextView>(R.id.verify_tv)

        clickableTV.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)

        clickableIV.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, FPass::class.java)
            startActivity(intent)
        }
    }
}