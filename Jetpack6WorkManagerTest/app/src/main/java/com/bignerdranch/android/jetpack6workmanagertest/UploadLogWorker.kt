package com.bignerdranch.android.jetpack6workmanagertest

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters


// 上传日志的Worker
class UploadLogWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    /**
     * 耗时的任务，在doWork()方法中执行
     *
     * 执行成功返回Result.success()
     * 执行失败返回Result.failure()
     * 需要重新执行返回Result.retry()
     */
    override fun doWork(): Result {
        //接收外面传递进来的数据
        val inputData: String? = inputData.getString("input_data")
        Log.e("UploadLogWorker", "doWork()->get inputData:$inputData")

        // 任务执行完成后返回数据
        val outputData: Data = Data.Builder().putString("output_data", "Task Success!").build()
        return Result.success(outputData)
    }
}