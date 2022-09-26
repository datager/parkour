package com.example.uiwidgettest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        button.setOnClickListener(this)
        supportActionBar?.hide()
    }

    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.button -> {
//                AlertDialog.Builder(this).apply {
//                    setTitle("This is Dialog")
//                    setMessage("something important")
//                    setPositiveButton("OK") { dialog, which -> }
//                    setNegativeButton("Cancel") { dialog, which -> }
//                    show()
//                }
//            }
//        }
    }
}