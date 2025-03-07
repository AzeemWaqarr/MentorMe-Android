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
import com.azeemwaqar.i210679.databinding.ActivitySearchResultBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class SearchResult : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivitySearchResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val linearLayout: LinearLayout = findViewById(R.id.SearchLinearLayout)

        val auth1 = FirebaseAuth.getInstance().currentUser

        val people = FirebaseDatabase.getInstance().getReference()
        people.addValueEventListener(object: ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children) {
                    if (i.hasChild("Gigs")) {

                        val gigView = layoutInflater.inflate(R.layout.searchresult_layout, null)

                        val textViewName = gigView.findViewById<TextView>(R.id.sr_name)
                        val textViewRole = gigView.findViewById<TextView>(R.id.sr_Role)
                        val textViewAvail = gigView.findViewById<TextView>(R.id.sr_AvailText)
                        val textViewPrice = gigView.findViewById<TextView>(R.id.sr_Rate)
                        val imageViewFav = gigView.findViewById<ImageView>(R.id.sr_FavIMG)
                        val imageViewAvail = gigView.findViewById<ImageView>(R.id.sr_AvailIMG)
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
                            val intent = Intent(this@SearchResult, Gig::class.java)
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




        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)


        clickableIV.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
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
        val chat: TextView = findViewById<TextView>(R.id.chat1)
        chat.setOnClickListener{
            val intent = Intent(this, chats::class.java)
            startActivity(intent)
        }
        val addm: TextView = findViewById<TextView>(R.id.addmentor_tv)
        addm.setOnClickListener{
            val intent = Intent(this, addMentor::class.java)
            startActivity(intent)
        }

    }
}
