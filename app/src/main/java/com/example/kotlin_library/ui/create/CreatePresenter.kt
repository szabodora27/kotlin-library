package com.example.kotlin_library.ui.create

import com.example.kotlin_library.interactor.books.BooksInteractor
import com.example.kotlin_library.ui.Presenter
import java.util.concurrent.Executor
import javax.inject.Inject

class CreatePresenter @Inject constructor(
    private val executor: Executor,
    private val interactor: BooksInteractor
) : Presenter<CreateScreen>() {

}