package com.bignerdranch.android.jetpack5roomtest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao?

    companion object {
        private const val DATABASE_NAME = "my_db"
        private var databaseInstance: MyDatabase? = null
        @Synchronized
        fun getInstance(ctx: Context): MyDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room
                    .databaseBuilder(ctx.applicationContext, MyDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return databaseInstance!!
        }
    }
}