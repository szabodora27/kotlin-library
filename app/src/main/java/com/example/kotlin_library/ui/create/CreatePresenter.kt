package com.example.kotlin_library.ui.create

import com.example.kotlin_library.interactor.books.BooksInteractor
import com.example.kotlin_library.interactor.books.events.AddBookEvent
import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class CreatePresenter @Inject constructor(
    private val executor: Executor,
    private val interactor: BooksInteractor
) : Presenter<CreateScreen>() {

    fun addBook(book: BookDb) {
        executor.execute {
            interactor.addBook(book)
        }
    }

    override fun attachScreen(screen: CreateScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: AddBookEvent) {
        if (screen != null) {
            if (event.book != null) {
                screen?.dismissDialog()
            }
        }
    }
}