package com.company0ne.coroutinesexample4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch { execute() }
    }

    private suspend fun execute() {
        val parentJob = CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..1000) {
                if (isActive) {
                    executeLoongRunningTask()
                    Log.d("TAG", i.toString())
                }
            }
        }
        delay(100)
        Log.d("TAG", "Canceling Job")
        parentJob.cancel()
        parentJob.join()
        Log.d("TAG", "Parent Completed")
    }

    private fun executeLoongRunningTask() {
        for (i in 1..1000000) {
        }
    }
}

