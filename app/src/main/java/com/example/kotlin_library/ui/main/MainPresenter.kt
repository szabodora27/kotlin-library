package com.example.kotlin_library.ui.main

import com.example.kotlin_library.interactor.books.BooksInteractor
import com.example.kotlin_library.interactor.books.events.AddBookEvent
import com.example.kotlin_library.interactor.books.events.GetBooksEvent
import com.example.kotlin_library.interactor.books.events.RemoveBookEvent
import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val executor: Executor,
    private val interactor: BooksInteractor
) : Presenter<MainScreen>() {

    fun getBooks() {
        executor.execute {
            interactor.getBooks()
        }
    }

    fun removeBook(book: BookDb, position: Int) {
        executor.execute {
            interactor.removeBook(book, position)
        }
    }

    override fun attachScreen(screen: MainScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetBooksEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.books != null) {
                    screen?.showBooks(event.books)
                }

            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: AddBookEvent) {
        if (screen != null) {
            if (event.book != null) {
                screen?.addBook(event.book!!)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: RemoveBookEvent) {
        if (screen != null) {
            if (event.book != null) {
                screen?.removeBook(event.book!!, event.position!!)
            }
        }
    }
}