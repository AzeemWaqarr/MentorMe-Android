package com.azeemwaqar.i210679

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class VerifyNo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify_no)
        val clickableIV: ImageView = findViewById<ImageView>(R.id.imageView5)

        clickableIV.setOnClickListener {
            // Handle the click event here
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        //Listening for Pressed key pads
        var index=0;
        var intArray = arrayOf(-1, -1, -1, -1, -1)
        val n1: TextView = findViewById<TextView>(R.id.n1)
        val n2: TextView = findViewById<TextView>(R.id.n2)
        val n3: TextView = findViewById<TextView>(R.id.n3)
        val n4: TextView = findViewById<TextView>(R.id.n4)
        val n5: TextView = findViewById<TextView>(R.id.n5)
        val n6: TextView = findViewById<TextView>(R.id.n6)
        val n7: TextView = findViewById<TextView>(R.id.n7)
        val n8: TextView = findViewById<TextView>(R.id.n8)
        val n9: TextView = findViewById<TextView>(R.id.n9)
        val n0: TextView = findViewById<TextView>(R.id.n0)

        val c1:TextView = findViewById<TextView>(R.id.c1)
        val c2:TextView = findViewById<TextView>(R.id.c2)
        val c3:TextView = findViewById<TextView>(R.id.c3)
        val c4:TextView = findViewById<TextView>(R.id.c4)
        val c5:TextView = findViewById<TextView>(R.id.c5)

        val beck:ImageView = findViewById<ImageView>(R.id.back)
        val verify_tv:TextView = findViewById<TextView>(R.id.verify_tv)

        beck.setOnClickListener{
            if(!c5.text.isEmpty())
            {
                c5.text = ""
            }
            else
            {
                if(!c4.text.isEmpty())
                {
                    c4.text = ""
                }
                else
                {
                    if(!c3.text.isEmpty())
                    {
                        c3.text = ""
                    }
                    else
                    {
                        if(!c2.text.isEmpty())
                        {
                            c2.text = ""
                        }
                        else
                        {
                            if(!c1.text.isEmpty())
                            {
                                c1.text = ""
                            }
                        }
                    }
                }
            }
            if(index>0)
            index-=1
        }
       n1.setOnClickListener{
           if(index<5)
           {
               intArray[index] = 1
               if(c1.text.isEmpty())
               {
                   c1.text = intArray[index].toString()
               }
               else
               {
                   if(c2.text.isEmpty())
                   {
                       c2.text = intArray[index].toString()
                   }
                   else
                   {
                       if(c3.text.isEmpty())
                       {
                           c3.text = intArray[index].toString()
                       }
                       else
                       {
                           if(c4.text.isEmpty())
                           {
                               c4.text = intArray[index].toString()
                           }
                           else
                           {
                               if(c5.text.isEmpty())
                               {
                                   c5.text = intArray[index].toString()
                               }
                           }
                       }
                   }
               }
               index += 1
           }

       }
        n2.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 2
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        n3.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 3
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        n4.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 4
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        n5.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 5
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        n6.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 6
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        n7.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 7
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        n8.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 8
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        n9.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 9
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        n0.setOnClickListener{
            if(index<5)
            {
                intArray[index] = 0
                if(c1.text.isEmpty())
                {
                    c1.text = intArray[index].toString()
                }
                else
                {
                    if(c2.text.isEmpty())
                    {
                        c2.text = intArray[index].toString()
                    }
                    else
                    {
                        if(c3.text.isEmpty())
                        {
                            c3.text = intArray[index].toString()
                        }
                        else
                        {
                            if(c4.text.isEmpty())
                            {
                                c4.text = intArray[index].toString()
                            }
                            else
                            {
                                if(c5.text.isEmpty())
                                {
                                    c5.text = intArray[index].toString()
                                }
                            }
                        }
                    }
                }
                index += 1
            }
        }
        verify_tv.setOnClickListener{
            if((!c5.text.isEmpty()) && (!c4.text.isEmpty())&& (!c3.text.isEmpty())&& (!c2.text.isEmpty())&& (!c1.text.isEmpty()))
            {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }

    }
}