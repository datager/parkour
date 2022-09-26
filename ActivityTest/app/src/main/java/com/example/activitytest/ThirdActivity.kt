package com.example.activitytest

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.third_activity.*

class ThirdActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ThirdActivity", "Task id is $taskId")
        setContentView(R.layout.third_activity)
        button3.setOnClickListener {
            ActivityCollector.finishAll()
        }
    }
}