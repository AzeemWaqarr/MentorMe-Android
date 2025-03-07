package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceView
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import io.agora.rtc2.RtcEngine
import io.agora.rtc2.IRtcEngineEventHandler
import io.agora.rtc2.RtcEngineConfig
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import io.agora.rtc2.ChannelMediaOptions
import io.agora.rtc2.Constants
import io.agora.rtc2.video.VideoCanvas

class VideoCall : AppCompatActivity() {
    private val PERMISSION_ID = 12
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
    private fun checkSelfPermission():Boolean{
        return !(ContextCompat.checkSelfPermission(
            this,REQUESTED_PERMISSION[0]
        )!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(
                    this,REQUESTED_PERMISSION[1]
                )!=PackageManager.PERMISSION_GRANTED)
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
        }
        catch(e: Exception)
        {
            showMessage(e.message.toString())
        }
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_call)
         val clickableIV: ImageView = findViewById<ImageView>(R.id.leave_video_call)

        if(!checkSelfPermission()){
            ActivityCompat
                .requestPermissions(
                    this,REQUESTED_PERMISSION,PERMISSION_ID
                )
        }
        setupVideoSdkEngine()
        val message = intent.getStringExtra("200")
        var x =0
        var count = 0

        var name= ""
        var image1 = ""
        if (message != null) {
            while(x < message.length)
            {
                if(count==1)
                {
                    image1+=message[x]
                }
                if(message[x].toChar() == ',')
                {
                    count = 1
                }
                if(count == 0)
                {
                    name+=message[x]
                }

                x+=1
            }
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
        val startCall:ImageView = findViewById<ImageView>(R.id.start_Call)
        joinCall()
        startCall.setOnClickListener{

        }
    }

    private fun leaveCall() {
        if(!isJoined)
        {
            showMessage("You Must Join A Channel First")
        }
        else{
            agoraEngine?.leaveChannel()
            showMessage("GoodBye :)")
            if(remoteSurfaceView!=null) remoteSurfaceView!!.visibility = GONE
            if(localSurfaceView!=null) localSurfaceView!!.visibility = GONE

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
            localSurfaceView!!.visibility = VISIBLE
            agoraEngine!!.startPreview()
            agoraEngine!!.joinChannel(token,channelName,uid,option)
        }
        else
        {
            showMessage("Permission Not Granted")
        }
    }

    private val mRtcEventHandler:IRtcEngineEventHandler =
        object : IRtcEngineEventHandler(){
            override fun onUserJoined(uid: Int, elapsed: Int) {
                showMessage("Remote User Joined $uid")
                runOnUiThread { setupRemoteVideo(uid) }
            }

            override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
                isJoined=true
                showMessage("Joined Channel $channel")
            }

            override fun onUserOffline(uid: Int, reason: Int) {
                showMessage("User Offline")

                runOnUiThread {
                    remoteSurfaceView!!.visibility = GONE
                }
            }
        }
    private fun setupRemoteVideo(uid:Int){
        remoteSurfaceView = SurfaceView(baseContext)
        remoteSurfaceView!!.setZOrderMediaOverlay(false)
        val remoteView = findViewById<FrameLayout>(R.id.remoteF)
        remoteView.addView(remoteSurfaceView)

        agoraEngine!!.setupRemoteVideo(
            VideoCanvas(
                remoteSurfaceView,
                VideoCanvas.RENDER_MODE_FIT,
                uid
            )
        )
    }
    private fun setupLocalVideo(){
        localSurfaceView = SurfaceView(baseContext)
        localSurfaceView!!.setZOrderMediaOverlay(true)
        val localView = findViewById<FrameLayout>(R.id.local)
        localView.addView(localSurfaceView)

        agoraEngine!!.setupLocalVideo(
            VideoCanvas(
                localSurfaceView,
                VideoCanvas.RENDER_MODE_FIT,
                0
            )
        )
    }
}