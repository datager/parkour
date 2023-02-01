package com.bignerdranch.android.jetpack6workmanagertest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startOneTimeTask()
        // startPeriodicWorkRequest();
        // startChainTask();
    }

    // 开启一个一次性任务
    private fun startOneTimeTask() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val inputData: Data = Data.Builder().putString("input_data", "Hello World!").build()
        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
            .setConstraints(constraints)//设置触发条件
            .setInitialDelay(10, TimeUnit.SECONDS) // 符合触发条件后，延迟10秒执行
            .setBackoffCriteria(BackoffPolicy.LINEAR, OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS) //设置指数退避算法
            .setInputData(inputData)
            .addTag("UploadTag")
            .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)
        //实时监听变化
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.id).observe(this@MainActivity) { workInfo ->
            Log.d("onChanged()->", "workInfo:$workInfo")
            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                val outputData = workInfo.outputData.getString("output_data")
                Log.d("onChanged()->", "doWork()->get outputData:$outputData")
                Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val TAG = "PeriodicTask"

    /**
     * 开启一个定期任务
     *
     * 通过设置TAG的方式来监听任务状态的变化，也可以使用ID的方式来监听
     */
    private fun startPeriodicWorkRequest() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        //不能少于15分钟
        val uploadWorkRequest = PeriodicWorkRequest.Builder(UploadLogWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints) //设置触发条件
            .addTag(TAG)
            .build()
        WorkManager.getInstance(this).enqueue(uploadWorkRequest)
        WorkManager.getInstance(this).getWorkInfosByTagLiveData(TAG)
            .observe(this@MainActivity) { workInfos -> Log.d("onChanged()->", "workInfo:" + workInfos!![0]) }
    }

    /**
     * 取消任务
     */
    private fun cancelAllWork() {
        WorkManager.getInstance(this@MainActivity).cancelAllWork()
    }

    /**
     * 开启任务链，任务的执行具有先后顺序
     */
    private fun startChainTask() {
        val constraints: Constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val compressWorkRequest = OneTimeWorkRequest.Builder(CompressLogWorker::class.java)
            .setConstraints(constraints) //设置触发条件
            .setInitialDelay(10, TimeUnit.SECONDS) //符合触发条件后，延迟10秒执行
            .build()
        val uploadWorkRequest = OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
            .setConstraints(constraints) //设置触发条件
            .setInitialDelay(10, TimeUnit.SECONDS) //符合触发条件后，延迟10秒执行
            .build()

        //实时监听变化
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorkRequest.id)
            .observe(this@MainActivity) { workInfo -> Log.d("onChanged()->", "workInfo:$workInfo") }
    }
}