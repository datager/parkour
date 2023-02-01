package com.bignerdranch.android.jetpacktest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBlueTooth()
    }

    override fun onResume() {
        super.onResume()
        pairBlueTooth()
    }

    override fun onPause() {
        super.onPause()
        stopBlueTooth()
    }

    private fun startBlueTooth() {}
    private fun pairBlueTooth() {}
    private fun stopBlueTooth() {}
}

