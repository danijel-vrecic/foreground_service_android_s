package com.android.foreground

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_start_work).setOnClickListener {
            startOneTimeWork()
        }
    }

    private fun startOneTimeWork() {
        WorkManager.getInstance(this).enqueueUniqueWork(
            OneTimeWork.TAG,
            ExistingWorkPolicy.REPLACE,
            OneTimeWork.newOneTimeWork()
        )
    }
}