package com.bignerdranch.android.jetpack4livedatatest

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniComponent()
    }

    private fun iniComponent() {
        val vm = ViewModelProvider(this)[TimerWithLiveDataViewModel::class.java]
        val liveData = vm.getCurrentSecond() as MutableLiveData<Int> // 得到ViewModel中的LiveData
        liveData.observe(this) { second -> (findViewById<View>(R.id.tvTime) as TextView).text = "TIME:$second" } // 对ViewModel中数据变化的观察
        findViewById<View>(R.id.btnResetTime).setOnClickListener { liveData.setValue(0) } // 对ViewModel中数据的更新
        vm.startTiming()
    }
}