package com.xuweic.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class PostMessageWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {


    override fun doWork(): Result {

        val httpJsonPharserManager = HttpJsonPharserManager(context)
        val annoyingExNotificationManager = AnnoyingExNotificationManager(context)
        httpJsonPharserManager.getAllPhrases {
            annoyingExNotificationManager.post(it.messages)
        }
        return Result.success()
    }

}