package com.example.kotlin_library.interactor

import android.content.Context
import com.example.kotlin_library.db.AppDatabase
import com.example.kotlin_library.interactor.books.BooksInteractor
import com.example.kotlin_library.network.LibraryApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {
    @Provides
    @Singleton
    fun provideBookInteractor(libraryApi: LibraryApi, context: Context, db: AppDatabase) =
        BooksInteractor(libraryApi, context, db)
}