package com.azeemwaqar.i210679

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.graphics.RectF
import android.graphics.PorterDuffXfermode
import com.azeemwaqar.i210679.databinding.ActivityBookedSessionBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation

class RoundedTransformation(private val radius: Float) : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val bitmap = Bitmap.createBitmap(source.width, source.height, source.config)
        val canvas = Canvas(bitmap)
        val paint = Paint()
        paint.isAntiAlias = true
        val rect = Rect(0, 0, source.width, source.height)
        val rectF = RectF(rect)
        canvas.drawRoundRect(rectF, radius, radius, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(source, rect, rect, paint)
        source.recycle()
        return bitmap
    }

    override fun key(): String = "rounded(radius=$radius)"
}
class BookedSession : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityBookedSessionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookedSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val iv_5: ImageView = findViewById<ImageView>(R.id.imageView5)
        iv_5.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        val currentUser1 = FirebaseAuth.getInstance().currentUser
        if (currentUser1 != null) {
            val userId = currentUser1.uid.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Bookings")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for(i in dataSnapshot.children) {
                        var date = i.child("date").value.toString()
                        var name = i.child("name").value.toString()
                        var role = i.child("role").value.toString()
                        var time = i.child("time").value.toString()

                        var date_tv = findViewById<TextView>(R.id.textView85)
                        var name_tv = findViewById<TextView>(R.id.textView83)
                        var role_tv = findViewById<TextView>(R.id.textView84)
                        var time_tv = findViewById<TextView>(R.id.textView86)

                        date_tv.text = date.toString()
                        name_tv.text = name.toString()
                        role_tv.text = role.toString()
                        time_tv.text = time.toString()


                        break

                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        }
        val currentUser3 = FirebaseAuth.getInstance().currentUser
        if (currentUser3 != null) {
            val userId = currentUser3.uid.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Photo")
            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.getValue(String::class.java)
                    Picasso.get().load(value).into(binding.imageView75)
                    Picasso.get()
                        .load(value)
                        .transform(RoundedTransformation(8f))
                        .fit()
                    .into(binding.imageView75)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                }
            })
        }
    }
}