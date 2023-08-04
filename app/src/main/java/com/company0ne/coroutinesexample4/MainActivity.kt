package com.company0ne.coroutinesexample4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                Log.d("TAG","Child Job Started")
                delay(5000)
                Log.d("TAG","Child Job Ended")
            }
            delay(3000)
            Log.d("TAG","Parent Ended")
        }
        //for cancellation if we uncomment it - if the delay is more than 1s then the parentJob will be suspended
//        delay(1000)
//        parentJob.cancel()

        parentJob.join()
        Log.d("TAG", "Parent Completed")
    }
}