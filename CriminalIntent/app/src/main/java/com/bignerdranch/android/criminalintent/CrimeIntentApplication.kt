package com.bignerdranch.android.criminalintent

import android.app.Application
import com.bignerdranch.android.criminalintent.database.CrimeRepository

class CriminalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CrimeRepository.initialize(this)
    }
}