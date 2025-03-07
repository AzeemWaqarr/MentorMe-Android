package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RemoteViews
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage

class SignUp : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

   
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = Firebase.auth



        val items = arrayOf("Select Country","Pakistan")
        val items1 = arrayOf("Select City","Sialkot", "Islamabad", "Gujranwala", "Lahore", "Peshawar")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = findViewById<Spinner>(R.id.country_s)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Handle the selected item
                val selectedItem = items[position]

            }


            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing when nothing is selected
            }
        }
        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items1)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner1: Spinner = findViewById<Spinner>(R.id.city_s)
        spinner1.adapter = adapter1


        spinner1.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Handle the selected item
                val selectedItem = items1[position]

            }


            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing when nothing is selected
            }
        }
        val clickableTV: TextView = findViewById<TextView>(R.id.textView12)

        clickableTV.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        val clickableTV1: TextView = findViewById<TextView>(R.id.signup_btn)

        clickableTV1.setOnClickListener {
            // Handle the click event here
            val username: TextView = findViewById<TextView>(R.id.username_tv)
            val email: TextView = findViewById<TextView>(R.id.email_tv)
            val phoneno: TextView = findViewById<TextView>(R.id.phone_tv)
            val country = spinner.selectedItem
            val city = spinner1.selectedItem
            val image = ""

            val password: TextView = findViewById<TextView>(R.id.password_tv)
            if((username.text.toString().isEmpty())||(email.text.toString().isEmpty())||(password.text.toString().isEmpty())||(phoneno.text.toString().isEmpty()))
            {
                Toast.makeText(this,"Fill All Fields!",Toast.LENGTH_SHORT).show()
            }
            else
            {
                if(password.text.toString().length<6)
                {
                    Toast.makeText(this,"Password Must be atleast 6 charachters long",Toast.LENGTH_SHORT).show()
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                        .addOnCompleteListener(this){ task ->
                            if(task.isSuccessful)
                            {
                                auth!!.signInWithEmailAndPassword(
                                    email.text.toString(),
                                    password.text.toString()
                                ).addOnSuccessListener {
                                    val auth1 = FirebaseAuth.getInstance().currentUser

                                    database = FirebaseDatabase.getInstance().getReference()
                                    val User = userData(auth1?.uid.toString(),username.text.toString(),email.text.toString(),phoneno.text.toString(),city.toString(),country.toString(),image.toString())
                                    if (auth1 != null) {
                                        database.child(auth1.uid.toString()).child("Userdata").setValue(User).addOnCompleteListener{
                                            username.text = ""
                                            email.text = ""
                                            phoneno.text = ""
                                            password.text = ""

                                        }
                                    }

                                    Toast.makeText(this,"Registration Successful",Toast.LENGTH_SHORT).show()
                                    val intent = Intent(this, VerifyNo::class.java)
                                    startActivity(intent)
                                }
                            }
                            else
                            {
                                Toast.makeText(this,"Registration Failed",Toast.LENGTH_SHORT).show()
                            }

                        }
                }
            }

        }
    }
}