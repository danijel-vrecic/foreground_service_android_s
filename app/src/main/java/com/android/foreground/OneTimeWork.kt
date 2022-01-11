package com.android.foreground

import android.content.Context
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class OneTimeWork(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork() = Result.success()

    companion object {
        const val TAG = "OneTimeWork"

        fun newOneTimeWork(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<OneTimeWork>()
                .addTag(TAG)
                .setInitialDelay(6, TimeUnit.SECONDS)
                .build()
        }
    }
}