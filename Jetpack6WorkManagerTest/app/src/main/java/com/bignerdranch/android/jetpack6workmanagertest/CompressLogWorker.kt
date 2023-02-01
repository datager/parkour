package com.bignerdranch.android.jetpack6workmanagertest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters


class CompressLogWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    /**
     * 耗时的任务，在doWork()方法中执行
     *
     * 执行成功返回Result.success()
     * 执行失败返回Result.failure()
     * 需要重新执行返回Result.retry()
     */
    override fun doWork(): Result {
        Log.e("CompressLogWorker", "doWork()")
        return Result.success()
    }
}