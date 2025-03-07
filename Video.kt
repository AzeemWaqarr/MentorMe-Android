package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Video : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView45)
        val message = intent.getStringExtra("200")
        val tv_37 : TextView = findViewById<TextView>(R.id.textView37)
        val iv_44 :ImageView = findViewById<ImageView>(R.id.imageView44)
        tv_37.setOnClickListener {
            val intent = Intent(this, Camera::class.java)
            intent.putExtra("200",message)
        }
        iv_44.setOnClickListener{
            val intent = Intent(this, Camera::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }
        var x =0
        var count = 0

        var name= ""
        var image1 = ""
        if (message != null) {
            while(x < message.length)
            {
                if(count==1)
                {
                    image1+=message[x]
                }
                if(message[x].toChar() == ',')
                {
                    count = 1
                }
                if(count == 0)
                {
                    name+=message[x]
                }

                x+=1
            }
        }
        clickableIV.setOnClickListener {
            // Handle the click event here
            finish()
            /*if(name.equals("ChatOpened")) {
                val intent = Intent(this, ChatOpened::class.java)
                intent.putExtra("EXTRA_MESSAGE", image1)
                startActivity(intent)
            }
            if(name.equals("CommunityOpened")) {
                val intent = Intent(this,CommunityOpened::class.java)
                intent.putExtra("EXTRA_MESSAGE", image1)
                startActivity(intent)
            }*/
        }
    }
}