package com.xuweic.annoyingex

import android.app.Application

class AnnoyingApp: Application() {

    lateinit var workAnnoyingManager: WorkAnnoyingManager
        private set


    override fun onCreate() {
        super.onCreate()

        workAnnoyingManager = WorkAnnoyingManager(this)
    }
}