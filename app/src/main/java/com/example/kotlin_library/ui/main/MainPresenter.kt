package com.example.kotlin_library.ui.main

import com.example.kotlin_library.interactor.books.BooksInteractor
import com.example.kotlin_library.ui.Presenter
import java.util.concurrent.Executor
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val executor: Executor,
    private val interactor: BooksInteractor
) : Presenter<MainScreen>() {

}