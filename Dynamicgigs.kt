package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.azeemwaqar.i210679.R.*

class Dynamicgigs : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_dynamicgigs)

        val linearLayout: LinearLayout = findViewById(id.linear_layout)
        val listOfGigs = GigUtils.getSampleGigs()

        for (gig in listOfGigs) {
            val gigView = layoutInflater.inflate(layout.gig_layout, null)

            val textViewName = gigView.findViewById<TextView>(id.name_tv1)
            val textViewRole = gigView.findViewById<TextView>(id.role_tv)
            val textViewAvail = gigView.findViewById<TextView>(id.availabality_tv)
            val textViewPrice = gigView.findViewById<TextView>(id.price_tv)
            val imageViewFav = gigView.findViewById<ImageView>(id.fav_IV)
            val imageViewAvail = gigView.findViewById<ImageView>(id.availability_IV)
            val favourite = gig.isFavorite
            textViewName.text = gig.name
            textViewRole.text = gig.description
            textViewAvail.text = gig.availability
            textViewPrice.text = gig.price
            if(favourite == true)
            {
                imageViewFav.setImageResource(R.drawable.vector__1_)
            }
            else
            {
                imageViewFav.setImageResource(R.drawable.vector__2_)
            }
            if(textViewAvail.text.toString().equals("Available"))
            {
                imageViewAvail.setImageResource(R.drawable.vector__4_)
                textViewAvail.setTextColor(Color.parseColor("#3ead00"))
            }
            else
            {
                imageViewAvail.setImageResource(R.drawable.vector__3_)
                textViewAvail.setTextColor(Color.parseColor("#918c8c"))
            }




            linearLayout.addView(gigView)
        }

    }
}