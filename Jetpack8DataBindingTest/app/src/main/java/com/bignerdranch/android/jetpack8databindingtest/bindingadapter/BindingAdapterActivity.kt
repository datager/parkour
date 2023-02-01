package com.bignerdranch.android.jetpack8databindingtest.bindingadapter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bignerdranch.android.jetpack8databindingtest.R
import com.bignerdranch.android.jetpack8databindingtest.databinding.ActivityBindingAdapterBinding


class BindingAdapterActivity : AppCompatActivity() {
    private var activityBindingAdapterBinding: ActivityBindingAdapterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBindingAdapterBinding = DataBindingUtil.setContentView(this, R.layout.activity_binding_adapter)
        with(activityBindingAdapterBinding) {
            this?.networkImage = "https://img1.doubanio.com/view/subject/l/public/s29612688.jpg"
            this?.localImage = R.mipmap.ic_launcher
            this?.imagePadding = 40
            this?.setClickHandler(ClickHandler())
        }
    }

    inner class ClickHandler {
        fun onClick(view: View?) {
            activityBindingAdapterBinding?.imagePadding = 180
        }
    }
}