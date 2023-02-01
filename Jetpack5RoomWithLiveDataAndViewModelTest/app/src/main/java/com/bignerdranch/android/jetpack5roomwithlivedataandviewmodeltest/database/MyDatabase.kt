package com.bignerdranch.android.jetpack5roomwithlivedataandviewmodeltest.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Student::class], exportSchema = false, version = 1)
abstract class MyDatabase() : RoomDatabase() {
    abstract fun studentDao(): StudentDao?

    companion object {
        private val DATABASE_NAME = "my_db"
        private var databaseInstance: MyDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabase? {
            if (databaseInstance == null) {
                databaseInstance = Room
                    .databaseBuilder(context.applicationContext, MyDatabase::class.java, DATABASE_NAME)
                    .createFromAsset("databases/student.db")
                    .fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_1_3, MIGRATION_3_4)
                    .build()
            }
            return databaseInstance
        }

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //do something
                Log.d("MyDatabase", "MIGRATION_1_2")
            }
        }
        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //do something
                Log.d("MyDatabase", "MIGRATION_2_3")
            }
        }
        private val MIGRATION_1_3: Migration = object : Migration(1, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //do something
                Log.d("MyDatabase", "MIGRATION_1_3")
            }
        }
        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.d("MyDatabase", "MIGRATION_3_4")
                database.execSQL(
                    "CREATE TABLE temp_Student (" +
                            "id INTEGER PRIMARY KEY NOT NULL," +
                            "name TEXT," +
                            "age TEXT)"
                )
                database.execSQL(
                    "INSERT INTO temp_Student (id, name, age) " +
                            "SELECT id, name, age FROM Student"
                )
                database.execSQL("DROP TABLE Student")
                database.execSQL("ALTER TABLE temp_Student RENAME TO Student")
            }
        }
    }
}