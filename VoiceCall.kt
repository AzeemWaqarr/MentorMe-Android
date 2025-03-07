package com.azeemwaqar.i210679

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.azeemwaqar.i210679.databinding.ActivityGigBinding
import com.azeemwaqar.i210679.databinding.ActivityVoiceCallBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngine
import io.agora.rtc2.RtcEngineConfig
import io.agora.rtc2.video.VideoCanvas

class VoiceCall : AppCompatActivity() {
    private val PERMISSION_ID = 13
    private val app_id = "f0a1922406ac4bd2807b7ffd50e17472"
    private val channelName = "mentorme"
    private val token = "007eJxTYPgmrbc5jPdNt9hkg8A54WwysRue/bvWGl+bHND6suu/2DYFhjSDRENLIyMTA7PEZJOkFCMLA/Mk87S0FFODVENzE3OjlJL/qQ2BjAxvuQ8xMjJAIIjPwZCbmleSX5SbysAAAMgQIX4="
    private val uid =0
    var isJoined = false
    private var agoraEngine: RtcEngine? = null
    private var localSurfaceView: SurfaceView? = null
    private var remoteSurfaceView: SurfaceView? = null
    private val REQUESTED_PERMISSION = arrayOf(
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.CAMERA
    )
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityVoiceCallBinding
    private fun checkSelfPermission():Boolean{
        return !(ContextCompat.checkSelfPermission(
            this,REQUESTED_PERMISSION[0]
        )!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this,REQUESTED_PERMISSION[1]
                )!= PackageManager.PERMISSION_GRANTED)
    }
    private fun showMessage(message:String)
    {
        runOnUiThread {
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
        }
    }
    private fun setupVideoSdkEngine(){
        try {
            val config = RtcEngineConfig()
            config.mContext = baseContext
            config.mAppId = app_id
            config.mEventHandler = mRtcEventHandler
            agoraEngine = RtcEngine.create(config)
            agoraEngine!!.enableVideo()
            agoraEngine!!.enableAudio()
        }
        catch(e: Exception)
        {
            showMessage(e.message.toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoiceCallBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val clickableIV: ImageView = findViewById<ImageView>(R.id.leave_video_call)
        if(!checkSelfPermission()){
            ActivityCompat
                .requestPermissions(
                    this,REQUESTED_PERMISSION,PERMISSION_ID
                )
        }
        setupVideoSdkEngine()
        val message = intent.getStringExtra("200")

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userId = message.toString()
            val userRef =
                FirebaseDatabase.getInstance().getReference().child(userId).child("Userdata").child("username")
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    if (dataSnapshot.exists()) {
                        val value = dataSnapshot.getValue(String::class.java)
                        val nameTextView = findViewById<TextView>(R.id.textView41)
                        nameTextView.text = value.toString()
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
                    Picasso.get().load(value).into(binding.imageView64)
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle errors
                }
            })
        }

        clickableIV.setOnClickListener {
            // Handle the click event here
            leaveCall()
            finish()

            /*if(name.equals("ChatOpened")) {
                val intent = Intent(this, ChatOpened::class.java)
                intent.putExtra("EXTRA_MESSAGE", image1)
                startActivity(intent)
            }
            if(name.equals("CommunityOpened")) {
                val intent = Intent(this,CommunityOpened::class.java)
                intent.putExtra("EXTRA_MESSAGE", image1)
                startActivity(intent)
            }*/
        }
        joinCall()
    }
    private fun leaveCall() {
        if(!isJoined)
        {
            showMessage("You Must Join A Channel First")
        }
        else{
            agoraEngine?.leaveChannel()
            showMessage("GoodBye :)")
            if(remoteSurfaceView!=null) remoteSurfaceView!!.visibility = View.GONE
            if(localSurfaceView!=null) localSurfaceView!!.visibility = View.GONE

            isJoined = false
        }

    }
    override fun onDestroy() {
        super.onDestroy()

        agoraEngine!!.stopPreview()
        agoraEngine!!.leaveChannel()

        Thread{
            RtcEngine.destroy()
            agoraEngine = null
        }.start()
    }

    private fun joinCall() {
        if(checkSelfPermission()){
            val option = ChannelMediaOptions()
            option.channelProfile = Constants.CHANNEL_PROFILE_COMMUNICATION

            option.clientRoleType = Constants.CLIENT_ROLE_BROADCASTER
            setupLocalVideo()
            localSurfaceView!!.visibility = View.VISIBLE
            agoraEngine!!.startPreview()
            agoraEngine!!.joinChannel(token,channelName,uid,option)
        }
        else
        {
            showMessage("Permission Not Granted")
        }
    }

    private val mRtcEventHandler: IRtcEngineEventHandler =
        object : IRtcEngineEventHandler(){
            override fun onUserJoined(uid: Int, elapsed: Int) {
                showMessage("Remote User Joined $uid")
                var status = findViewById<TextView>(R.id.textView40)
                status.text = "Connected"
                runOnUiThread { setupRemoteVideo(uid) }
            }

            override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
                isJoined=true
                showMessage("Joined Channel $channel")
                var status = findViewById<TextView>(R.id.textView40)
                status.text = "Connecting..."
            }

            override fun onUserOffline(uid: Int, reason: Int) {
                showMessage("User Offline")

                runOnUiThread {
                    remoteSurfaceView!!.visibility = View.GONE
                }
            }
        }
    private fun setupRemoteVideo(uid:Int){
        remoteSurfaceView = SurfaceView(baseContext)
        remoteSurfaceView!!.setZOrderMediaOverlay(true)
        val remoteView = findViewById<FrameLayout>(R.id.remote_FV)
        remoteView.addView(remoteSurfaceView)

        agoraEngine!!.setupRemoteVideo(
            VideoCanvas(
                remoteSurfaceView,
                VideoCanvas.RENDER_MODE_ADAPTIVE,
                uid
            )
        )
    }
    private fun setupLocalVideo(){
        localSurfaceView = SurfaceView(baseContext)
        localSurfaceView!!.setZOrderMediaOverlay(true)
        val localView = findViewById<FrameLayout>(R.id.local_FV)
        localView.addView(localSurfaceView)

        agoraEngine!!.setupLocalVideo(
            VideoCanvas(
                localSurfaceView,
                VideoCanvas.RENDER_MODE_ADAPTIVE,
                0
            )
        )
    }
}