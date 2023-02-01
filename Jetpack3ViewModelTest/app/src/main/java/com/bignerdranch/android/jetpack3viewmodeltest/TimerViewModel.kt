package com.bignerdranch.android.jetpack3viewmodeltest

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

class TimerViewModel : ViewModel() {
    private var timer: Timer? = null
    private var currentSecond: Int = 0
    private val TAG = this.javaClass.name

    // 开始计时
    fun startTiming() {
        if (timer == null) {
            currentSecond = 0
            timer = Timer()
            val timerTask: TimerTask = object : TimerTask() {
                override fun run() {
                    currentSecond++
                    if (onTimeChangeListener != null) {
                        onTimeChangeListener!!.onTimeChanged(currentSecond)
                    }
                }
            }
            timer?.schedule(timerTask, 1000, 1000) //延迟1秒执行, 间隔1秒
        }
    }

    // 通过接口的方式，完成对调用者的通知，这种方式不是太好，更好的方式是通过LiveData组件来实现
    interface OnTimeChangeListener {
        fun onTimeChanged(second: Int)
    }

    private var onTimeChangeListener: OnTimeChangeListener? = null

    fun setOnTimeChangeListener(onTimeChangeListener: OnTimeChangeListener?) {
        this.onTimeChangeListener = onTimeChangeListener
    }

    // 由于屏幕旋转导致的Activity重建，该方法不会被调用
    // 只有ViewModel已经没有任何Activity与之有关联，系统则会调用该方法，你可以在此清理资源
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared()");
        timer?.cancel()
    }
}