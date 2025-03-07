package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Camera : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView45)

        val tv_35:TextView = findViewById<TextView>(R.id.textView35)

        val iv_50:ImageView = findViewById<ImageView>(R.id.imageView50)

        val message = intent.getStringExtra("200")
        val message1 = message
        tv_35.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            intent.putExtra("200",message1)
            startActivity(intent)
        }

        iv_50.setOnClickListener{
            val intent = Intent(this, Video::class.java)
            intent.putExtra("200",message1)
            startActivity(intent)
        }

        //val message = intent.getStringExtra("200")
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