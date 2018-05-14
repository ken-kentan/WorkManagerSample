package com.example.workmanagersample

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workManager = WorkManager.getInstance()

        schedule_button.setOnClickListener {
            val workRequest = PeriodicWorkRequest.Builder(
                    SampleWorker::class.java,
                    15L, TimeUnit.MINUTES)
                    .addTag("sample")
                    .build()

            workManager.enqueue(workRequest)
        }

        cancel_button.setOnClickListener {
            workManager.cancelAllWorkByTag("sample")
        }

        workManager.getStatusesByTag("sample").observe(this@MainActivity, Observer {
            val result = it ?: return@Observer

            val sb = StringBuilder()
            result.forEach { sb.append("${it.id}: ${it.state.name}\n") }

            Toast.makeText(this@MainActivity, sb.toString(), Toast.LENGTH_LONG).show()
        })
    }
}
