package com.azeemwaqar.i210679

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.azeemwaqar.i210679.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso

class EditProfile : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_edit_profile)
        setContentView(binding.root)
        init()

        val iv_72:ImageView = findViewById<ImageView>(R.id.imageView72)
        iv_72.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }




        val items = arrayOf("Pakistan")
        val items1 = arrayOf("Sialkot", "Islamabad", "Gujranwala", "Lahore", "Peshawar")

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner: Spinner = findViewById<Spinner>(R.id.country_s)
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Handle the selected item
                var selectedItem = items[position]

            }


            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing when nothing is selected
            }
        }
        var adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items1)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner1: Spinner = findViewById<Spinner>(R.id.city_s)
        spinner1.adapter = adapter1


        spinner1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Handle the selected item
                var selectedItem = items1[position]

            }


            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Do nothing when nothing is selected
            }
        }
        val verify:TextView = findViewById<TextView>(R.id.verify_tv)
        verify.setOnClickListener{
            val auth1 = FirebaseAuth.getInstance().currentUser
            val database1 = FirebaseDatabase.getInstance().getReference()
            val nameTextView = findViewById<TextView>(R.id.username_tv)
            val emailTextView = findViewById<TextView>(R.id.email_tv)
            val phonenoTextView = findViewById<TextView>(R.id.phone_tv)
            val country = spinner.selectedItem
            val city = spinner1.selectedItem
            val User = userData(auth1?.uid.toString(),nameTextView.text.toString(),emailTextView.text.toString(),phonenoTextView.text.toString(),city.toString(),country.toString())
            if (auth1 != null) {
                database1.child(auth1.uid.toString()).child("Userdata").setValue(User)
            }
        }
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
                FirebaseDatabase.getInstance().getReference().child(userId).child("Photo")
            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(String::class.java)
                    Picasso.get().load(value).into(binding.imageView73)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                }
            })
        }
        if (currentUser1 != null) {
            val userId = currentUser1.uid.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Userdata")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val user = dataSnapshot.getValue(userData::class.java)
                        if (user != null) {
                            // Access user properties
                            val nameTextView = findViewById<TextView>(R.id.username_tv)
                            val emailTextView = findViewById<TextView>(R.id.email_tv)
                            val phonenoTextView = findViewById<TextView>(R.id.phone_tv)
                            val country = findViewById<Spinner>(R.id.country_s)
                            val city = findViewById<Spinner>(R.id.city_s)

                            nameTextView.text = user.username
                            emailTextView.text = user.email
                            phonenoTextView.text = user.contactNo
                            val countryIndex = (country.adapter as ArrayAdapter<String>).getPosition(user.country)
                            country.setSelection(countryIndex)
                            val cityIndex = (city.adapter as ArrayAdapter<String>).getPosition(user.city)
                            city.setSelection(cityIndex)


                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }
        val pickPhoto = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.imageView73.setImageURI(it)

                val reference = storage.getReference().child("images/${it!!.lastPathSegment}")
                reference.putFile(it!!).addOnSuccessListener {
                    reference.downloadUrl.addOnSuccessListener {
                        val URL = it.toString()
                        val currentUser = auth.currentUser
                        currentUser?.let { user ->
                            val db = FirebaseDatabase.getInstance().getReference().child(currentUser.uid)
                            db.child("Photo").setValue(URL)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Image upload success", Toast.LENGTH_SHORT)
                                        .show()

                                }
                        }
                    }
                }
            }
        )
        binding.imageView777.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                pickPhoto.launch("image/*")
            }
            else{
                Toast.makeText(this,"Permission not Granted",Toast.LENGTH_SHORT).show()
            }
        }
        binding.imageView73.setOnClickListener{
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                pickPhoto.launch("image/*")
            }
            else{
                Toast.makeText(this,"Permission not Granted",Toast.LENGTH_SHORT).show()
            }
        }
    }
}