package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    override fun onStart() {
        super.onStart()
        val currentuser: FirebaseUser? = auth.currentUser

        if (currentuser!=null)
        {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {


        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val clickableTextView: TextView = findViewById<TextView>(R.id.textView6)
        val clickableTV1: TextView = findViewById<TextView>(R.id.textView7)
        val clickableTV2: TextView = findViewById<TextView>(R.id.textView8)

        clickableTextView.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        clickableTV1.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, FPass::class.java)
            startActivity(intent)
        }
        clickableTV2.setOnClickListener {
            // Handle the click event here
            val username: EditText = findViewById<EditText>(R.id.username_tv)
            val password: EditText = findViewById<EditText>(R.id.pass_et)
            if((username.text.toString().isEmpty())||(password.text.toString().isEmpty()))
            {
                Toast.makeText(this,"Fill All Fields!", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.signInWithEmailAndPassword(username.text.toString(),password.text.toString())
                    .addOnCompleteListener{task ->
                        if(task.isSuccessful)
                        {
                            Toast.makeText(this,"Sign In Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, HomeScreen::class.java)
                            startActivity(intent)
                            finish()
                        }
                        else
                        {
                            Toast.makeText(this,"Sign In Failed", Toast.LENGTH_SHORT).show()

                        }


                    }
            }

        }
    }
}