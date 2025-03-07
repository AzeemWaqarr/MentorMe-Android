package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.azeemwaqar.i210679.databinding.ActivityGigBinding
import com.azeemwaqar.i210679.databinding.ActivityMentorReviewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class mentorReview : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMentorReviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMentorReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("200").toString()

        val back: ImageView = findViewById<ImageView>(R.id.imageView100)
        back.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = message.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Gigs").child("name")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(String::class.java)
                        val nameTextView = findViewById<TextView>(R.id.textView74)
                        nameTextView.text = value.toString()
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
                    Picasso.get().load(value).into(binding.imageView108)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                }
            })
        }
        val rb = findViewById<RatingBar>(R.id.ratingBar3)
        rb.rating=0f
        rb.stepSize=1.0f
        val feedback : TextView = findViewById<TextView>(R.id.textView71)
        feedback.setOnClickListener {
            val desc = findViewById<EditText>(R.id.editTextText).text.toString()
            val rating = rb.rating.toString()
            auth = FirebaseAuth.getInstance()
            val auth1 = auth.currentUser

            if (auth1 != null) {
                if(auth1.uid.toString().equals(message.toString())) {
                    Toast.makeText(this,"Cannot Review Own Gig",Toast.LENGTH_SHORT).show()
                } else {
                    database = FirebaseDatabase.getInstance().getReference()

                    if (auth1 != null) {
                        database.child(message.toString()).child("MyReviews").child(auth.uid.toString()).child("Description")
                            .setValue(desc)

                        database.child(message.toString()).child("MyReviews").child(auth.uid.toString()).child("Rating")
                            .setValue(rating)

                    }

                    val intent = Intent(this, HomeScreen::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}