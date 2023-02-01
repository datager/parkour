package com.bignerdranch.android.jetpack3viewmodeltest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.jetpack3viewmodeltest.TimerViewModel.OnTimeChangeListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniComponent()
    }

    private fun iniComponent() {
        val tvTime = findViewById<TextView>(R.id.tvTime)
        // 通过 ViewModelProvider 得到 ViewModel，如果 ViewModel 不存在就创建一个新的，如果已经存在就直接返回已经存在的
        val timerViewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
        timerViewModel.setOnTimeChangeListener(object : OnTimeChangeListener {
            override fun onTimeChanged(second: Int) {
                runOnUiThread { tvTime.text = "TIME:$second" } // 更新UI界面
            }
        })
        timerViewModel.startTiming()
    }

}