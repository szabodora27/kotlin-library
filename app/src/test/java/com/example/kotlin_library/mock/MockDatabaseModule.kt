package com.example.kotlin_library.mock

import android.content.Context
import com.example.kotlin_library.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockDatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        return MockAppDatabase.getInstance(context)
    }
}