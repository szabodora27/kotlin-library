package com.example.kotlin_library.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlin_library.dao.BookDao
import com.example.kotlin_library.model.db.BookDb

@Database(entities = [(BookDb::class)], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private var sInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "bookDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }
}