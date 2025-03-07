package com.azeemwaqar.i210679

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.azeemwaqar.i210679.HomeScreen
import com.azeemwaqar.i210679.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val channelid = "notification_channel"
const val channelname = "com.azeemwaqar.i210679"
// Create a class to track app lifecycle
class AppLifecycleObserver : Application.ActivityLifecycleCallbacks {
    private var appInForeground = false

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        appInForeground = true
    }

    override fun onActivityPaused(activity: Activity) {
        appInForeground = false
    }

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}

    fun isAppInForeground(): Boolean {
        return appInForeground
    }
}

// Update your MyFirebaseMessagingService class
class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val appLifecycleObserver = AppLifecycleObserver()

    override fun onCreate() {
        super.onCreate()
        // Register the app lifecycle observer
        //registerActivityLifecycleCallbacks(appLifecycleObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unregister the app lifecycle observer to avoid memory leaks
        //unregisterActivityLifecycleCallbacks(appLifecycleObserver)
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")

        // Here you can send the token to your server or perform any other logic
        // to store or manage the token as needed.
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let {
            if (appLifecycleObserver.isAppInForeground()) {
                // If the app is in the foreground, handle the notification accordingly.
                //handleForegroundNotification(it)
                generateNotification(it.title!!, it.body!!)
            } else {
                // If the app is in the background, generate a notification.
                generateNotification(it.title!!, it.body!!)
            }
        }
    }

    private fun handleForegroundNotification(notification: RemoteMessage.Notification) {
        // Handle the notification when the app is in the foreground.
        // For example, you can show a dialog or update the UI directly.
        Log.d(TAG, "Foreground Notification: ${notification.title}, ${notification.body}")
    }
    @SuppressLint("RemoteViewLayout")
    fun getRemoteView(title: String, description: String) : RemoteViews
    {
        val remoteView = RemoteViews("com.azeemwaqar.i210679", R.layout.notification_layout)
        remoteView.setTextViewText(R.id.noti_title, title)
        remoteView.setTextViewText(R.id.noti_desc, description)
        remoteView.setImageViewResource(R.id.imageView101,R.drawable.app_icon)

        return remoteView
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun generateNotification(title: String, description: String) {
        // Generate notification as before
        val intent = Intent(this, HomeScreen::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelid)
            .setSmallIcon(R.drawable.app_icon)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title, description))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelid, channelname, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0, builder.build())
    }
}