package com.xuweic.annoyingex

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class FetchJsonWorker(private val context: Context, workParams: WorkerParameters): Worker(context, workParams) {

    private lateinit var allPhrase: AllPhrases

    override fun doWork(): Result {

        val httpJsonPharserManager = HttpJsonPharserManager(context)

        httpJsonPharserManager.getAllPhrases { allPhrases ->
            allPhrase = allPhrases
        }
        return Result.success()
    }
}