package com.azeemwaqar.i210679

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging

class HomeScreen : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.i("My Token", token)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val linearLayout: LinearLayout = findViewById(R.id.linear_layout)
        val linearLayout1: LinearLayout = findViewById(R.id.linear_layout_1)
        val linearLayout2: LinearLayout = findViewById(R.id.linear_layout_2)
        val listOfGigs = GigUtils.getSampleGigs()

        val people = FirebaseDatabase.getInstance().getReference()
        people.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children) {
                    if (i.hasChild("Gigs")) {
                        val gigView = layoutInflater.inflate(R.layout.gig_layout, null)

                        val textViewName = gigView.findViewById<TextView>(R.id.name_tv1)
                        val textViewRole = gigView.findViewById<TextView>(R.id.role_tv)
                        val textViewAvail = gigView.findViewById<TextView>(R.id.availabality_tv)
                        val textViewPrice = gigView.findViewById<TextView>(R.id.price_tv)
                        val imageViewFav = gigView.findViewById<ImageView>(R.id.fav_IV)
                        val imageViewAvail = gigView.findViewById<ImageView>(R.id.availability_IV)
                        val favourite = false
                        textViewName.text = i.child("Gigs").child("name").value.toString()
                        textViewRole.text = i.child("Gigs").child("role").value.toString()
                        textViewAvail.text = i.child("Gigs").child("avail").value.toString()
                        textViewPrice.text = i.child("Gigs").child("price").value.toString()
                        if (favourite == true) {
                            imageViewFav.setImageResource(R.drawable.vector__1_)
                        } else {
                            imageViewFav.setImageResource(R.drawable.vector__2_)
                        }
                        if (textViewAvail.text.toString().equals("Available")) {
                            imageViewAvail.setImageResource(R.drawable.vector__4_)
                            textViewAvail.setTextColor(Color.parseColor("#3ead00"))
                        } else {
                            imageViewAvail.setImageResource(R.drawable.vector__3_)
                            textViewAvail.setTextColor(Color.parseColor("#918c8c"))
                        }

                        gigView.setOnClickListener {
                            val intent = Intent(this@HomeScreen, Gig::class.java)
                            intent.putExtra("200", i.key.toString())
                            startActivity(intent)
                        }


                        linearLayout.addView(gigView)
                        //linearLayout1.addView(gigView)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        val people1 = FirebaseDatabase.getInstance().getReference()
        people1.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children) {
                    if (i.hasChild("Gigs")) {
                        val gigView = layoutInflater.inflate(R.layout.gig_layout, null)

                        val textViewName = gigView.findViewById<TextView>(R.id.name_tv1)
                        val textViewRole = gigView.findViewById<TextView>(R.id.role_tv)
                        val textViewAvail = gigView.findViewById<TextView>(R.id.availabality_tv)
                        val textViewPrice = gigView.findViewById<TextView>(R.id.price_tv)
                        val imageViewFav = gigView.findViewById<ImageView>(R.id.fav_IV)
                        val imageViewAvail = gigView.findViewById<ImageView>(R.id.availability_IV)
                        val favourite = false
                        textViewName.text = i.child("Gigs").child("name").value.toString()
                        textViewRole.text = i.child("Gigs").child("role").value.toString()
                        textViewAvail.text = i.child("Gigs").child("avail").value.toString()
                        textViewPrice.text = i.child("Gigs").child("price").value.toString()
                        if (favourite == true) {
                            imageViewFav.setImageResource(R.drawable.vector__1_)
                        } else {
                            imageViewFav.setImageResource(R.drawable.vector__2_)
                        }
                        if (textViewAvail.text.toString().equals("Available")) {
                            imageViewAvail.setImageResource(R.drawable.vector__4_)
                            textViewAvail.setTextColor(Color.parseColor("#3ead00"))
                        } else {
                            imageViewAvail.setImageResource(R.drawable.vector__3_)
                            textViewAvail.setTextColor(Color.parseColor("#918c8c"))
                        }
                        gigView.setOnClickListener {
                            val intent = Intent(this@HomeScreen, Gig::class.java)
                            intent.putExtra("200", i.key.toString())
                            startActivity(intent)
                        }



                        //linearLayout.addView(gigView)
                        linearLayout1.addView(gigView)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        val people2 = FirebaseDatabase.getInstance().getReference()
        people2.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children) {
                    if (i.hasChild("Gigs")) {
                        val gigView = layoutInflater.inflate(R.layout.gig_layout, null)

                        val textViewName = gigView.findViewById<TextView>(R.id.name_tv1)
                        val textViewRole = gigView.findViewById<TextView>(R.id.role_tv)
                        val textViewAvail = gigView.findViewById<TextView>(R.id.availabality_tv)
                        val textViewPrice = gigView.findViewById<TextView>(R.id.price_tv)
                        val imageViewFav = gigView.findViewById<ImageView>(R.id.fav_IV)
                        val imageViewAvail = gigView.findViewById<ImageView>(R.id.availability_IV)
                        val favourite = false
                        textViewName.text = i.child("Gigs").child("name").value.toString()
                        textViewRole.text = i.child("Gigs").child("role").value.toString()
                        textViewAvail.text = i.child("Gigs").child("avail").value.toString()
                        textViewPrice.text = i.child("Gigs").child("price").value.toString()
                        if (favourite == true) {
                            imageViewFav.setImageResource(R.drawable.vector__1_)
                        } else {
                            imageViewFav.setImageResource(R.drawable.vector__2_)
                        }
                        if (textViewAvail.text.toString().equals("Available")) {
                            imageViewAvail.setImageResource(R.drawable.vector__4_)
                            textViewAvail.setTextColor(Color.parseColor("#3ead00"))
                        } else {
                            imageViewAvail.setImageResource(R.drawable.vector__3_)
                            textViewAvail.setTextColor(Color.parseColor("#918c8c"))
                        }
                        gigView.setOnClickListener {
                            val intent = Intent(this@HomeScreen, Gig::class.java)
                            intent.putExtra("200", i.key.toString())
                            startActivity(intent)
                        }



                        //linearLayout.addView(gigView)
                        linearLayout2.addView(gigView)

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        //Notification
        val noti: ImageView = findViewById<ImageView>(R.id.imageView6)
        noti.setOnClickListener{
            val intent = Intent(this, Noti::class.java)
            startActivity(intent)
        }

        val message = "HomeScreen"
        val chat: TextView = findViewById<TextView>(R.id.chat)
        chat.setOnClickListener{
            val intent = Intent(this, chats::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }

        val prof: TextView = findViewById<TextView>(R.id.profile)
        prof.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }
        val sear: TextView = findViewById<TextView>(R.id.search)
        sear.setOnClickListener{
            val intent = Intent(this, Search::class.java)
            intent.putExtra("200",message)
            startActivity(intent)
        }
        auth = FirebaseAuth.getInstance()
        val signout: TextView = findViewById<TextView>(R.id.signout_btn)
        signout.setOnClickListener{
            auth.signOut()
                val intent = Intent(this, Login::class.java)
                intent.putExtra("200", message)
                startActivity(intent)
        }
        val addm: TextView = findViewById<TextView>(R.id.addmentor_tv)
        addm.setOnClickListener{
            val intent = Intent(this, addMentor::class.java)
            startActivity(intent)
        }

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = currentUser.uid.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Userdata")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val user = dataSnapshot.getValue(userData::class.java)
                        if (user != null) {
                            // Access user properties
                            val nameTextView = findViewById<TextView>(R.id.name_display)
                            nameTextView.text = user.username
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
               })
            }

    }
}