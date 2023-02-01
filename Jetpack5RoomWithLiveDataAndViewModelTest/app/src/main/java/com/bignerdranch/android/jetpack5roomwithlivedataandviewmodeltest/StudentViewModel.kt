package com.bignerdranch.android.jetpack5roomwithlivedataandviewmodeltest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bignerdranch.android.jetpack5roomwithlivedataandviewmodeltest.database.MyDatabase
import com.bignerdranch.android.jetpack5roomwithlivedataandviewmodeltest.database.Student


class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val myDatabase: MyDatabase?
    val liveDataStudent: LiveData<List<Student?>?>?

    init {
        myDatabase = MyDatabase.getInstance(application)
        liveDataStudent = myDatabase!!.studentDao()!!.getStudentList()
    }
}