package com.bignerdranch.android.jetpack5roomwithlivedataandviewmodeltest.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student?)

    @Delete
    fun deleteStudent(student: Student?)

    @Update
    fun updateStudent(student: Student?)

    @Query("SELECT * FROM student")
    fun getStudentList(): LiveData<List<Student?>?>? //希望监听学生表的变化，为其加上LiveData

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int): Student?
}