package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context // 全局context
        const val TOKEN = "7PyxAWkE6xGKzTCa" // 接口token
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
