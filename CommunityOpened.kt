package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class CommunityOpened : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_opened)

        var cam:TextView = findViewById<TextView>(R.id.camera1)
        val message = intent.getStringExtra("EXTRA_MESSAGE")
        cam.setOnClickListener{
            var message1 = "CommunityOpened"
            message1+=","
            message1+=message
            val intent = Intent(this, Camera::class.java)
            intent.putExtra("200",message1)
            startActivity(intent)
        }
        val home: TextView = findViewById<TextView>(R.id.homescreen)
        home.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val profi: TextView = findViewById<TextView>(R.id.profile3)
        profi.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        val addm: TextView = findViewById<TextView>(R.id.addmentor_tv)
        addm.setOnClickListener{
            val intent = Intent(this, addMentor::class.java)
            startActivity(intent)
        }

        var vidc:TextView = findViewById<TextView>(R.id.videocall)
        vidc.setOnClickListener {
            var message1 = "CommunityOpened"
            message1+=","
            message1+=message
            val intent = Intent(this, VideoCall::class.java)
            intent.putExtra("200",message1)
            startActivity(intent)
        }

        var voic:TextView = findViewById<TextView>(R.id.voicecall)
        voic.setOnClickListener {
            var message1 = "CommunityOpened"
            message1+=","
            message1+=message
            val intent = Intent(this, VoiceCall::class.java)
            intent.putExtra("200",message1)
            startActivity(intent)
        }
        //val message = intent.getStringExtra("EXTRA_MESSAGE")

        var count=0
        var name = ""
        var image1 = ""
        var x = 0
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
        val tv_name:TextView = findViewById<TextView>(R.id.textView17)
        tv_name.text = image1


        val image:ImageView = findViewById<ImageView>(R.id.imageView41)
        if(name.equals("1"))
        {
            image.setImageResource(R.drawable.image_5)
        }
        if(name.equals("2"))
        {
            image.setImageResource(R.drawable.image_6)
        }
        if(name.equals("3"))
        {
            image.setImageResource(R.drawable.image_7)
        }
        if(name.equals("4"))
        {
            image.setImageResource(R.drawable.image_8)
        }
        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)

        clickableIV.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, chats::class.java)
            startActivity(intent)
        }
    }
}