package com.bignerdranch.android.jetpack8databindingtest.eventhandle

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bignerdranch.android.jetpack8databindingtest.R
import com.bignerdranch.android.jetpack8databindingtest.databinding.ActivityEventHandleBinding


class EventHandleActivity : AppCompatActivity() {
    class EventHandleListener(private val context: Context) {
        fun onButtonClicked(view: View?) {
            Toast.makeText(context, "I am clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}