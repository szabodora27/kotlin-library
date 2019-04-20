package com.example.kotlin_library.ui

import android.content.Context
import com.example.kotlin_library.interactor.books.BooksInteractor
import com.example.kotlin_library.ui.create.CreatePresenter
import com.example.kotlin_library.ui.details.DetailsPresenter
import com.example.kotlin_library.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun mainPresenter(executor: Executor, booksInteractor: BooksInteractor) = MainPresenter(executor, booksInteractor)

    @Provides
    @Singleton
    fun detailsPresenter(executor: Executor, booksInteractor: BooksInteractor) = DetailsPresenter(executor, booksInteractor)

    @Provides
    @Singleton
    fun createPresenter(executor: Executor, booksInteractor: BooksInteractor) = CreatePresenter(executor, booksInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
}