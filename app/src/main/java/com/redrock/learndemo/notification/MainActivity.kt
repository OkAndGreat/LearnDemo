package com.redrock.learndemo.notification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.redrock.learndemo.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val request = OneTimeWorkRequest.Builder(DailyWorker::class.java).build()

        WorkManager.getInstance(applicationContext)
            .enqueue(request)

    }
}