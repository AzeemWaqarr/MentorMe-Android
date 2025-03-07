package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.azeemwaqar.i210679.databinding.ActivityChatsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class chats : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityChatsBinding
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayout: LinearLayout = findViewById(R.id.ChatLinearLayout)
        val listOfchats = ChatUtils.getSampleGigs()
        val auth1 = FirebaseAuth.getInstance().currentUser

        val people = FirebaseDatabase.getInstance().getReference()
        people.addValueEventListener(object: ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children) {
                    if (auth1 != null) {
                        if(!(i.key.toString().equals(auth1.uid.toString())))
                        {


                    val chatView = layoutInflater.inflate(R.layout.chat_layout, null)

                        val textViewName = chatView.findViewById<TextView>(R.id.chatName)
                        val textViewMessage = chatView.findViewById<TextView>(R.id.MessageChat)
                        val imageViewFav = chatView.findViewById<ImageView>(R.id.ChatImage)
                        textViewName.text = i.child("Userdata").child("username").value.toString()
                        val newMessage = 0;
                        var string:String
                        if (newMessage>0) {
                            string = newMessage.toString()
                            if(newMessage == 1) {
                                string += " New Message"
                            }
                            else {
                                string += " New Message"
                            }
                            textViewMessage.text = string
                            textViewMessage.setTextColor(Color.parseColor("#a70606"))

                        } else {
                            textViewMessage.text ="No New Messages"
                            textViewMessage.setTextColor(Color.parseColor("#596363"))
                        }


                        chatView.setOnClickListener {
                            val intent = Intent(this@chats, ChatOpened::class.java)
                            intent.putExtra("200", i.key.toString())
                            startActivity(intent)
                        }
                        if (i.key != null) {
                            val userId = i.key.toString()
                            val userRef =
                                FirebaseDatabase.getInstance().getReference().child(userId).child("Photo")
                            userRef.addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(dataSnapshot: DataSnapshot) {
                                    val value = dataSnapshot.getValue(String::class.java)
                                    Picasso.get().load(value).into((chatView.findViewById<ImageView>(R.id.ChatImage)))
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // Handle errors
                                }
                            })
                        }


                        linearLayout.addView(chatView)
                        //linearLayout1.addView(gigView)
                        }

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })



        val message = intent.getStringExtra("200")
        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)

        clickableIV.setOnClickListener {
            // Handle the click event here
            if(message.equals("HomeScreen")) {
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
            }
        }
        val home: TextView = findViewById<TextView>(R.id.homescreen)
        home.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val profi: TextView = findViewById<TextView>(R.id.profile1)
        profi.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        val sear: TextView = findViewById<TextView>(R.id.search)
        sear.setOnClickListener{
            val intent = Intent(this, Search::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }
        val addm: TextView = findViewById<TextView>(R.id.addmentor_tv)
        addm.setOnClickListener{
            val intent = Intent(this, addMentor::class.java)
            startActivity(intent)
        }



        val com1:TextView = findViewById<TextView>(R.id.comunity1)
        val com2:TextView = findViewById<TextView>(R.id.comunity2)
        val com3:TextView = findViewById<TextView>(R.id.comunity3)
        val com4:TextView = findViewById<TextView>(R.id.comunity4)

        /*com1.setOnClickListener{
            var msg = ""
            msg+=1
            msg+=","
            msg+=name1.text
            val intent = Intent(this, CommunityOpened::class.java)

            // Add data to Intent
            intent.putExtra("EXTRA_MESSAGE", msg)

            // Start the new activity
            startActivity(intent)
        }
        com2.setOnClickListener{
            var msg = ""
            msg+=2
            msg+=","
            msg+=name2.text
            val intent = Intent(this, CommunityOpened::class.java)

            // Add data to Intent
            intent.putExtra("EXTRA_MESSAGE", msg)

            // Start the new activity
            startActivity(intent)
        }
        com3.setOnClickListener{
            var msg = ""
            msg+=3
            msg+=","
            msg+=name3.text
            val intent = Intent(this, CommunityOpened::class.java)

            // Add data to Intent
            intent.putExtra("EXTRA_MESSAGE", msg)

            // Start the new activity
            startActivity(intent)
        }
        com4.setOnClickListener{
            var msg = ""
            msg+=4
            msg+=","
            msg+="Azeem"
            val intent = Intent(this, CommunityOpened::class.java)

            // Add data to Intent
            intent.putExtra("EXTRA_MESSAGE", msg)

            // Start the new activity
            startActivity(intent)
        }*/






    }
}