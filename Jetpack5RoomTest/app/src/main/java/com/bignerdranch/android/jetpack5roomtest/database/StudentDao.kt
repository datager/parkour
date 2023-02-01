package com.bignerdranch.android.jetpack5roomtest.database

import androidx.room.*

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student?)

    @Delete
    fun deleteStudent(student: Student?)

    @Update
    fun updateStudent(student: Student?)

    @get:Query("SELECT * FROM student")
    val studentList: List<Student?>?

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int): Student?
}