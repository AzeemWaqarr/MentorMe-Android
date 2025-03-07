package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Search : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val message = intent.getStringExtra("200")

        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)

        clickableIV.setOnClickListener {
            // Handle the click event here
            if(message.equals("HomeScreen")) {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
            }


        }
        val prof: TextView = findViewById<TextView>(R.id.profile)
        prof.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }
        val chat: TextView = findViewById<TextView>(R.id.chat)
        chat.setOnClickListener{
            val intent = Intent(this, chats::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }
        val home: TextView = findViewById<TextView>(R.id.homescreen)
        home.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }
        val sr: TextView = findViewById<TextView>(R.id.searchresult)
        sr.setOnClickListener{
            val intent = Intent(this, SearchResult::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }
        val addm: TextView = findViewById<TextView>(R.id.addmentor_tv)
        addm.setOnClickListener{
            val intent = Intent(this, addMentor::class.java)
            startActivity(intent)
        }
    }
}