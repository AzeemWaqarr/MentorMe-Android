package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class mentorDeets : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var database1: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor_deets)
        val back: ImageView = findViewById<ImageView>(R.id.imageView121)
        back.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val auth1 = FirebaseAuth.getInstance().currentUser
        database = FirebaseDatabase.getInstance().getReference()
        val add: TextView = findViewById<TextView>(R.id.textView82)
        add.setOnClickListener{
            val price: EditText = findViewById<EditText>(R.id.editTextText2)
            val Role: EditText = findViewById<EditText>(R.id.editTextText3)
            if (auth1 != null) {
                database.child(auth1.uid.toString()).child("Gigs").child("price").setValue(price.text.toString())
                database.child(auth1.uid.toString()).child("Gigs").child("role").setValue(Role.text.toString())
            }
            var gname=""
            var gavail=""
            val currentUser1 = FirebaseAuth.getInstance().currentUser
            if (currentUser1 != null) {
                val userId = currentUser1.uid.toString()
                val userRef =
                    FirebaseDatabase.getInstance().getReference().child(userId).child("Gigs").child("name")
                userRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        gname = dataSnapshot.getValue(String::class.java).toString()

                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle errors
                    }
                })
                val userRef1 =
                    FirebaseDatabase.getInstance().getReference().child(userId).child("Gigs").child("avail")
                userRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        gavail = dataSnapshot.getValue(String::class.java).toString()

                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle errors
                    }
                })
            }
            var gigs = GigUtils.getSampleGigs()
            val newGig = GigUtils.gigstest(
                gname,
                Role.text.toString(),
                gavail,
                price.text.toString(),
                false
            )
            val auth2 = FirebaseAuth.getInstance().currentUser

            database1 = FirebaseDatabase.getInstance().getReference().child("Gigs")

            gigs = gigs.toMutableList().apply { add(newGig) }

            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
    }

}