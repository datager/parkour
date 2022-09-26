package com.example.jetpacktest

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_main.*

data class User(var firstName: String, var lastName: String, var age: Int)

// 这里我们为了让MyObserver能够感知到Activity的生命周期，需要专门在
// MainActivity中重写相应的生命周期方法，然后再通知给MyObserver。这种实现方式虽然是
// 可以正常工作的，但是不够优雅，需要在Activity中编写太多额外的逻辑
class MainActivity : AppCompatActivity() {
    lateinit var observer: MyObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

class MyObserver : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d("MyObserver", "activityStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d("MyObserver", "activityStop")
    }
}

class MainViewModel(countReserved: Int) : ViewModel() {
    private val userLiveData = MutableLiveData<User>()
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }
}

