package com.bignerdranch.android.criminalintent

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignerdranch.android.criminalintent.database.CrimeDatabase
import com.bignerdranch.android.criminalintent.database.migration_2_3
import com.bignerdranch.android.criminalintent.database.migration_3_4
import java.io.File
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {
    private val database: CrimeDatabase = Room.databaseBuilder(context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME)
        .createFromAsset("crime-database")
        .fallbackToDestructiveMigration()
        .addMigrations(migration_2_3, migration_3_4)
        .build()
    private val crimeDao = database.crimeDao()
    private val executor = Executors.newSingleThreadExecutor()

    private val filesDir = context.applicationContext.filesDir
    fun getPhotoFile(crime: Crime): File = File(filesDir, crime.photoFileName)
    fun getCrimes(): LiveData<List<Crime>> = crimeDao.getCrimes()
    fun getCrime(id: UUID): LiveData<Crime?> = crimeDao.getCrime(id)
    fun addCrime(c: Crime) {
        executor.execute { crimeDao.addCrime(c) }
    }

    fun updateCrime(c: Crime) {
        executor.execute { crimeDao.updateCrime(c) }
    }

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}