package com.xuweic.annoyingex

import android.content.Context
import androidx.work.*
import kotlin.random.Random
import java.util.concurrent.TimeUnit

class WorkAnnoyingManager(private var context: Context){

    private var workManager: WorkManager = WorkManager.getInstance(context)


    fun startAnnoying() {
        if (!isRunning()) {
            stopAnnoying()
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<PostMessageWorker>(20, TimeUnit.MINUTES)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(AWTY_WORK_REQUEST_TAG)
            .build()

        workManager.enqueue(workRequest)
    }

    private fun isRunning():Boolean {
        return when (workManager.getWorkInfosByTag(AWTY_WORK_REQUEST_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
                WorkInfo.State.ENQUEUED -> true
                else -> false
        }
    }

    fun startFetch() {
        if (!isRunningFetch()) {
            stopFetch()
        }

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val workRequest = PeriodicWorkRequestBuilder<FetchJsonWorker>(2, TimeUnit.DAYS)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setConstraints(constraints)
            .addTag(FETCH_JSON_TAG)
            .build()

        workManager.enqueue(workRequest)
    }

    fun stopFetch() {
        workManager.cancelAllWorkByTag(FETCH_JSON_TAG)
    }

    private fun isRunningFetch():Boolean {
        return when (workManager.getWorkInfosByTag(FETCH_JSON_TAG).get().firstOrNull()?.state) {
            WorkInfo.State.RUNNING,
            WorkInfo.State.ENQUEUED -> true
            else -> false
        }
    }



    fun stopAnnoying() {
        workManager.cancelAllWorkByTag(AWTY_WORK_REQUEST_TAG)
    }

    companion object {
        const val AWTY_WORK_REQUEST_TAG = "AWTY_WORK_REQUEST_TAG"
        const val FETCH_JSON_TAG = "FETCH_JSON_TAG"
    }


}