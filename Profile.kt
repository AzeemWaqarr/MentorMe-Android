package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.azeemwaqar.i210679.databinding.ActivityEditProfileBinding
import com.azeemwaqar.i210679.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class Profile : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private lateinit var binding: ActivityProfileBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val linearLayout: LinearLayout = findViewById(R.id.linear_layout)
        val linearLayout1: LinearLayout = findViewById(R.id.linear_layout_1)
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
                            val intent = Intent(this@Profile, Gig::class.java)
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
                            val intent = Intent(this@Profile, Gig::class.java)
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



        val iv_70:ImageView = findViewById<ImageView>(R.id.imageView70)
        iv_70.setOnClickListener{
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }
        val iv_5:ImageView = findViewById<ImageView>(R.id.imageView5)
        iv_5.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val home: TextView = findViewById<TextView>(R.id.homescreen1)
        home.setOnClickListener{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        }
        val chat: TextView = findViewById<TextView>(R.id.chat1)
        chat.setOnClickListener{
            val intent = Intent(this, chats::class.java)
            startActivity(intent)
        }
        val sear: TextView = findViewById<TextView>(R.id.search)
        sear.setOnClickListener{
            val intent = Intent(this, Search::class.java)
            startActivity(intent)
        }
        val addm: TextView = findViewById<TextView>(R.id.addmentor_tv)
        addm.setOnClickListener{
            val intent = Intent(this, addMentor::class.java)
            startActivity(intent)
        }
        val tv_42:TextView = findViewById<TextView>(R.id.textView42)
        tv_42.setOnClickListener {
            val intent = Intent(this, BookedSession::class.java)
            startActivity(intent)
        }
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()

        val currentUser1 = FirebaseAuth.getInstance().currentUser
        if (currentUser1 != null) {
            val userId = currentUser1.uid.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Photo")
            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(String::class.java)
                    Picasso.get().load(value).into(binding.imageView69)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                }
            })
        }
        init()
    }
    private fun init()
    {
        database = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        val currentUser1 = FirebaseAuth.getInstance().currentUser
        if (currentUser1 != null) {
            val userId = currentUser1.uid.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("CoverPhoto")
            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(String::class.java)
                    Picasso.get().load(value).into(binding.imageView66)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                }
            })
        }

        val pickPhoto = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.imageView66.setImageURI(it)

                val reference = storage.getReference().child("images/${it!!.lastPathSegment}")
                reference.putFile(it!!).addOnSuccessListener {
                    reference.downloadUrl.addOnSuccessListener {
                        val URL = it.toString()
                        val currentUser = auth.currentUser
                        currentUser?.let { user ->
                            val db = FirebaseDatabase.getInstance().getReference().child(currentUser.uid)
                            db.child("CoverPhoto").setValue(URL)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Image upload success", Toast.LENGTH_SHORT)
                                        .show()

                                }
                        }
                    }
                }
            }
        )
        binding.imageView66.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                pickPhoto.launch("image/*")
            }
            else{
                Toast.makeText(this,"Permission not Granted", Toast.LENGTH_SHORT).show()
            }
        }
        binding.imageView67.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                pickPhoto.launch("image/*")
            }
            else{
                Toast.makeText(this,"Permission not Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}