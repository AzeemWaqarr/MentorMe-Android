package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class addMentor : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mentor)
        val items = arrayOf("Available", "Unavailable")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = findViewById<Spinner>(R.id.status_s)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            )
            {
                // Handle the selected item
                val selectedItem = items[position]

            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing when nothing is selected
            }
        }

        val back: ImageView = findViewById<ImageView>(R.id.imageView109)
        back.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }

        val upload: TextView = findViewById<TextView>(R.id.textView71)
        upload.setOnClickListener{
            val user: TextView = findViewById<TextView>(R.id.username_tv)
            val desc: TextView = findViewById<TextView>(R.id.description_tv)
            val avail = spinner.selectedItem

            val auth1 = FirebaseAuth.getInstance().currentUser
            database = FirebaseDatabase.getInstance().getReference()


            if (auth1 != null) {
                database.child(auth1.uid.toString()).child("Gigs").child("name").setValue(user.text.toString())
                database.child(auth1.uid.toString()).child("Gigs").child("description").setValue(desc.text.toString())
                database.child(auth1.uid.toString()).child("Gigs").child("avail").setValue(avail.toString())
            }

            val intent = Intent(this, mentorDeets::class.java)
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
            startActivity(intent)
        }
        val home: TextView = findViewById<TextView>(R.id.homescreen)
        home.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val chat: TextView = findViewById<TextView>(R.id.chat1)
        chat.setOnClickListener{
            val intent = Intent(this, chats::class.java)
            startActivity(intent)
        }

    }
}