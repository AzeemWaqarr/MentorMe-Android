package com.azeemwaqar.i210679

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.azeemwaqar.i210679.databinding.ActivityCalendarBinding
import com.azeemwaqar.i210679.databinding.ActivityGigBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class calendar : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityCalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val back: ImageView = findViewById<ImageView>(R.id.imageView100)
        back.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val message = intent.getStringExtra("200").toString()

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = message.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Gigs").child("name")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(String::class.java)
                        val nameTextView = findViewById<TextView>(R.id.textView73)
                        nameTextView.text = value.toString()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }
        var role= ""
        val currentUser1 = FirebaseAuth.getInstance().currentUser
        if (currentUser1 != null) {
            val userId = message.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Gigs").child("role")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(String::class.java)
                        role = value.toString()
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

        val timeslot1 = findViewById<TextView>(R.id.ts1)
        val timeslot2 = findViewById<TextView>(R.id.ts2)
        val timeslot3 = findViewById<TextView>(R.id.ts3)
        var choice = timeslot1.text.toString()


        timeslot1.setOnClickListener {
            timeslot1.setBackgroundResource(R.drawable.rectgreen)
            timeslot2.setBackgroundResource(R.drawable.scrollrect)
            timeslot3.setBackgroundResource(R.drawable.scrollrect)
            choice = timeslot1.text.toString()
            timeslot1.setTextColor(Color.parseColor("#FFFFFF"))
            timeslot2.setTextColor(Color.parseColor("#000000"))
            timeslot3.setTextColor(Color.parseColor("#000000"))
        }
        timeslot2.setOnClickListener {
            timeslot2.setBackgroundResource(R.drawable.rectgreen)
            timeslot1.setBackgroundResource(R.drawable.scrollrect)
            timeslot3.setBackgroundResource(R.drawable.scrollrect)
            choice = timeslot2.text.toString()
            timeslot2.setTextColor(Color.parseColor("#FFFFFF"))
            timeslot1.setTextColor(Color.parseColor("#000000"))
            timeslot3.setTextColor(Color.parseColor("#000000"))
        }
        timeslot3.setOnClickListener {
            timeslot3.setBackgroundResource(R.drawable.rectgreen)
            timeslot2.setBackgroundResource(R.drawable.scrollrect)
            timeslot1.setBackgroundResource(R.drawable.scrollrect)
            choice = timeslot3.text.toString()
            timeslot3.setTextColor(Color.parseColor("#FFFFFF"))
            timeslot2.setTextColor(Color.parseColor("#000000"))
            timeslot1.setTextColor(Color.parseColor("#000000"))
        }

        val book = findViewById<TextView>(R.id.textView71)
        var bdate = ""
        book.setOnClickListener {
            val calendarView = findViewById<CalendarView>(R.id.calendarView)
            calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                // Month is 0-based, so you need to add 1 to get the correct month
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                bdate+=dayOfMonth.toString()
                bdate+=" "
                bdate+=(month+1).toString()
                bdate+=" "
                bdate+=year.toString()
            }


            auth = FirebaseAuth.getInstance()
            val auth1 = auth.currentUser

            if (auth1 != null) {
                if(auth1.uid.toString().equals(message.toString())) {
                    Toast.makeText(this,"Cannot Book Own Gig",Toast.LENGTH_SHORT).show()
                } else {
                    database = FirebaseDatabase.getInstance().getReference()

                    if (auth1 != null) {
                        database.child(auth1.uid.toString()).child("Bookings").child(message.toString())
                            .child("name").setValue(binding.textView73.text.toString())

                        database.child(auth1.uid.toString()).child("Bookings").child(message.toString())
                            .child("role").setValue(role.toString())
                        database.child(auth1.uid.toString()).child("Bookings").child(message.toString())
                            .child("time").setValue(choice.toString())
                        database.child(auth1.uid.toString()).child("Bookings").child(message.toString())
                            .child("date").setValue(bdate.toString())
                    }
                    val intent = Intent(this, HomeScreen::class.java)
                    startActivity(intent)
                }
            }

        }
    }
}