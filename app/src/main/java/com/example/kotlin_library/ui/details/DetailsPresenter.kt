package com.example.kotlin_library.ui.details

import com.example.kotlin_library.interactor.books.BooksInteractor
import com.example.kotlin_library.interactor.books.events.GetBookEvent
import com.example.kotlin_library.ui.Presenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.concurrent.Executor
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val executor: Executor,
    private val interactor: BooksInteractor
) : Presenter<DetailsScreen>() {

    fun getBook(id: Int) {
        executor.execute {
            interactor.getBook(id)
        }
    }

    override fun attachScreen(screen: DetailsScreen) {
        super.attachScreen(screen)
        EventBus.getDefault().register(this)
    }

    override fun detachScreen() {
        EventBus.getDefault().unregister(this)
        super.detachScreen()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventMainThread(event: GetBookEvent) {
        if (event.throwable != null) {
            event.throwable?.printStackTrace()
            if (screen != null) {
                screen?.showNetworkError(event.throwable?.message.orEmpty())
            }
        } else {
            if (screen != null) {
                if (event.book != null) {
                    screen?.showBook(event.book)
                }
            }
        }
    }
}