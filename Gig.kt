package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.azeemwaqar.i210679.databinding.ActivityGigBinding
import com.azeemwaqar.i210679.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class Gig : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityGigBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGigBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val message = intent.getStringExtra("200")


        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = message.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Gigs").child("name")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(String::class.java)
                        val nameTextView = findViewById<TextView>(R.id.textView65)
                        nameTextView.text = value.toString()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }
        val currentUser1 = FirebaseAuth.getInstance().currentUser
        if (currentUser1 != null) {
            val userId = message.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Gigs").child("role")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(String::class.java)
                        val roleTextView = findViewById<TextView>(R.id.textView66)
                        roleTextView.text = value.toString()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }
        val currentUser2 = FirebaseAuth.getInstance().currentUser
        if (currentUser2 != null) {
            val userId = message.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Gigs").child("description")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(String::class.java)
                        val descTextView = findViewById<TextView>(R.id.textView67)
                        descTextView.text = value.toString()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }
        val currentUser3 = FirebaseAuth.getInstance().currentUser
        if (currentUser3 != null) {
            val userId = message.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Photo")
            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(String::class.java)
                    Picasso.get().load(value).into(binding.imageView95)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                }
            })
        }





        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)

        clickableIV.setOnClickListener {
            // Handle the click event here
            /*val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)*/
            finish()
        }
        val review: TextView = findViewById<TextView>(R.id.textView68)

        review.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, mentorReview::class.java)
            intent.putExtra("200", message.toString())
            startActivity(intent)
        }
        val book: TextView = findViewById<TextView>(R.id.textView70)

        book.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, calendar::class.java)
            intent.putExtra("200", message.toString())
            startActivity(intent)
        }
    }
}