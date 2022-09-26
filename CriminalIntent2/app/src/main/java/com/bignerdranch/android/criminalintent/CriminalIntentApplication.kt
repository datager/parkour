package com.bignerdranch.android.criminalintent

import android.app.Application
import java.util.*

class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)

//        val titles = listOf("张三", "李四", "王二麻子")
//        Thread {
//            val c = CrimeRepository.get()
//            for (i in 0 until 1) {
//                c.addCrime(Crime(UUID.randomUUID(), titles.random(), Date(), true, true))
//                c.addCrime(Crime(UUID.randomUUID(), titles.random(), Date(), false, false))
//            }
//        }.start()
    }
}