package com.example.kotlin_library

import android.content.Context
import com.example.kotlin_library.interactor.books.BooksInteractor
import com.example.kotlin_library.ui.create.CreatePresenter
import com.example.kotlin_library.ui.details.DetailsPresenter
import com.example.kotlin_library.ui.main.MainPresenter
import com.example.kotlin_library.utils.UiExecutor
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class TestModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideMainPresenter(executor: Executor, interactor: BooksInteractor) =
        MainPresenter(executor, interactor)

    @Provides
    @Singleton
    fun provideDetailsPresenter(executor: Executor, interactor: BooksInteractor) =
        DetailsPresenter(executor, interactor)

    @Provides
    @Singleton
    fun createShowPresenter(executor: Executor, interactor: BooksInteractor) =
        CreatePresenter(executor, interactor)

    @Provides
    @Singleton
    fun provideNetworkExecutor(): Executor = UiExecutor()
}