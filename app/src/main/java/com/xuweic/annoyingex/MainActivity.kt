package com.xuweic.annoyingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var app: HttpJsonPharserManager
    private lateinit var allPhrase: AllPhrases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val annoyingApp = (application as AnnoyingApp)
        val workAnnoyingManager = annoyingApp.workAnnoyingManager
        //val annoyingExNotificationManager = awypApp.annoyingExNotificationManager

        Log.i("chenxuweiyi", "here I am")

        btAnnoying.setOnClickListener {
            workAnnoyingManager.startAnnoying()
        }

        btStop.setOnClickListener {
            workAnnoyingManager.stopAnnoying()
            //annoyingExNotificationManager.post()
        }

    }
}
