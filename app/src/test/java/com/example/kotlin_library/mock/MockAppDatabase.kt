package com.example.kotlin_library.mock

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import com.example.kotlin_library.db.AppDatabase
import com.example.kotlin_library.db.DateConverter
import com.example.kotlin_library.model.db.BookDb

@Database(entities = [(BookDb::class)], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MockAppDatabase : AppDatabase() {
    abstract override fun bookDao(): MockBookDao

    companion object {
        private var sInstance: MockAppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MockAppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .inMemoryDatabaseBuilder(context.applicationContext, MockAppDatabase::class.java)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }

    }
}