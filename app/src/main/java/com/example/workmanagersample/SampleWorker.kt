package com.example.workmanagersample

import android.util.Log
import androidx.work.Worker

class SampleWorker : Worker() {
    override fun doWork(): WorkerResult {
        Log.d("SampleWorker", "doWork")

        return WorkerResult.SUCCESS
    }
}