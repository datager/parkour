package com.bignerdranch.android.jetpack8databindingtest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.jetpack8databindingtest.bindingadapter.BindingAdapterActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
//        val b: ActivitySimpleTextViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_simple_text_view)
//        val book = Book("Linux", "Linus")
//        book.rating = 5
//        b.book = book //将对象传递到layout中

//        val b: ActivityEventHandleBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_handle)
//        b.eventHandler = EventHandleActivity.EventHandleListener(this)

    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, BindingAdapterActivity::class.java)
        startActivity(intent)
    }
}