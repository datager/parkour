package com.bignerdranch.android.cametaxapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.camera.core.ImageCapture
import androidx.camera.core.VideoCapture
import androidx.camera.video.Recording
import com.bignerdranch.android.cametaxapp.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService


typealias LumaListener = (luma: Double) -> Unit

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    private var imageCapture: ImageCapture? = null

    private var videoCapture: VideoCapture? = null
    private var recording: Recording? = null

    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater) // 因为配置文件
        setContentView(viewBinding.root)
    }
}