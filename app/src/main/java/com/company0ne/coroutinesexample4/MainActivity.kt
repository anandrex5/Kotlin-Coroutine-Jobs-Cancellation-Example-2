package com.company0ne.coroutinesexample4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch { execute() }
    }

    private suspend fun execute() {
        val parentJob = GlobalScope.launch(Dispatchers.Main) {
//            Log.d("TAG", "Parent - $coroutineContext")

            Log.d("TAG","Parent Started")



            //its automatic inherit the parent context
            //we can also run in another thread like launch(Dispatchers.IO)
            val childJob = launch{
//                Log.d("TAG", "Child - $coroutineContext")
                try {
                    Log.d("TAG","Child Job Started")
                    delay(5000)
                    Log.d("TAG","Child Job Ended")
                } catch (e: CancellationException) {
                    Log.d("TAG","Child Job Cancelled")
                }
            }
            delay(3000)
            childJob.cancel()
            Log.d("TAG","Parent Ended")
        }
        parentJob.join()
        Log.d("TAG", "Parent Completed")
    }
}