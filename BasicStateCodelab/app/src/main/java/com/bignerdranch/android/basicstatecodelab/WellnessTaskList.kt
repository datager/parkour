package com.bignerdranch.android.basicstatecodelab

fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }