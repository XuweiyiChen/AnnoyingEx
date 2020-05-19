package com.xuweic.annoyingex

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.properties.Delegates

class AnnoyingExNotificationManager(private val context: Context) {

    private val notificationManagerCompat = NotificationManagerCompat.from(context)
    private lateinit var chosenPhrase:String
    private var chosenIndex by Delegates.notNull<Int>()

    init {
        create()
    }

    fun post(phrases: List<String>) {

        chosenPhrase = phrases.random()
        chosenIndex = phrases.indexOf(chosenPhrase)

        val dealsIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingDealsIntent = PendingIntent.getActivity(context, 0, dealsIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(context, FUN_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_mail_black_24dp)
            .setContentTitle("You know Who I am")
            .setContentText(chosenPhrase)
            .setContentIntent(pendingDealsIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        notificationManagerCompat.notify(chosenIndex, notification)
    }

    private fun create() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "fun notification"
            val descriptionText = "All messages from a great guy"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(FUN_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            notificationManagerCompat.createNotificationChannel(channel)
        }
    }
    companion object {
        const val FUN_CHANNEL_ID = "FUN CHANNEL ID"
    }
}