package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.azeemwaqar.i210679.databinding.ActivityChatOpenedBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.RtcEngineConfig
import io.agora.rtc2.video.VideoCanvas

class ChatOpened : AppCompatActivity() {
    private lateinit var binding : ActivityChatOpenedBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatOpenedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message1 = intent.getStringExtra("200")

        val currentUser1 = FirebaseAuth.getInstance().currentUser
        if (currentUser1 != null) {
            val userId = message1.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Userdata").child("username")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(String::class.java)
                        val roleTextView = findViewById<TextView>(R.id.textView17)
                        roleTextView.text = value.toString()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }

        val setname:TextView = findViewById<TextView>(R.id.textView17)
        var img1:ImageView = findViewById<ImageView>(R.id.imageView35)
        var img2:ImageView = findViewById<ImageView>(R.id.imageView36)

        var cam:TextView = findViewById<TextView>(R.id.camera)
        val message = intent.getStringExtra("EXTRA_MESSAGE")
        cam.setOnClickListener{
            var message1 = "ChatOpened"
            message1+=","
            message1+=message
            val intent = Intent(this, Camera::class.java)
            intent.putExtra("200",message1)
            startActivity(intent)
        }
        var vidc:TextView = findViewById<TextView>(R.id.videocall)
        vidc.setOnClickListener {
            val intent = Intent(this, VideoCall::class.java)
            intent.putExtra("200",message1)
            startActivity(intent)
        }
        var voic:TextView = findViewById<TextView>(R.id.voicecall)
        voic.setOnClickListener {
            val intent = Intent(this, VoiceCall::class.java)
            intent.putExtra("200",message1)
            startActivity(intent)
        }


        var x = 0
        var name = ""
        var image =""
        var count =0
        if (message != null) {
            while(x < message.length)
            {
                if(count==1)
                {
                    image+=message[x]
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
        setname.text = name
        if(image.equals("imageView27"))
        {
            img1.setImageResource(R.drawable.image_5)
            img2.setImageResource(R.drawable.image_5)
        }
        if(image.equals("imageView28"))
        {
            img1.setImageResource(R.drawable.image_6)
            img2.setImageResource(R.drawable.image_6)
        }
        if(image.equals("imageView31"))
        {
            img1.setImageResource(R.drawable.image_7)
            img2.setImageResource(R.drawable.image_7)
        }


        val home: TextView = findViewById<TextView>(R.id.homescreen)
        home.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val profi: TextView = findViewById<TextView>(R.id.profile2)
       profi.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        val addm: TextView = findViewById<TextView>(R.id.addmentor_tv)
        addm.setOnClickListener{
            val intent = Intent(this, addMentor::class.java)
            startActivity(intent)
        }
        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)

        clickableIV.setOnClickListener {
            // Handle the click event here
                val intent = Intent(this, chats::class.java)
                val msg1= "HomeScreen"
                intent.putExtra("200",msg1)
                startActivity(intent)
        }
    }
}