package com.example.kotlin_library.interactor.books

import android.content.Context
import android.util.Log
import com.example.kotlin_library.db.AppDatabase
import com.example.kotlin_library.interactor.books.events.AddBookEvent
import com.example.kotlin_library.interactor.books.events.GetBookEvent
import com.example.kotlin_library.interactor.books.events.GetBooksEvent
import com.example.kotlin_library.interactor.books.events.RemoveBookEvent
import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.model.network.Book
import com.example.kotlin_library.network.LibraryApi
import org.greenrobot.eventbus.EventBus
import org.modelmapper.ModelMapper
import javax.inject.Inject

class BooksInteractor @Inject constructor(private var libraryApi: LibraryApi, private var context: Context) {

    fun getBooks() {
        val event = GetBooksEvent()

        try {
            val books = AppDatabase.getInstance(context).bookDao().getAllBook().toMutableList()
            var bookDbList = mutableListOf<BookDb>()

            if (books.isEmpty()) {
                val booksCall = libraryApi.getBooks()

                val response = booksCall.execute()
                Log.d("Reponse", response.body().toString())
                if (response.code() != 200) {
                    throw Exception("Result code is not 200")
                }

                response.body()?.forEach {
                    val book = getBookDetails(it.id!!)
                    bookDbList.add(saveBook(book))
                }
            } else {
                bookDbList = books
            }

            event.code = 200
            event.books = bookDbList
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun getBook(id: Int) {
        val event = GetBookEvent()

        try {
            val book = AppDatabase.getInstance(context).bookDao().getBookById(id)

            event.code = 200
            event.book = book
            EventBus.getDefault().post(event)
        } catch (e: Exception) {
            event.throwable = e
            EventBus.getDefault().post(event)
        }
    }

    fun addBook(book: BookDb) {
        AppDatabase.getInstance(context).bookDao().insert(book)

        val event = AddBookEvent(book = book)
        EventBus.getDefault().post(event)
    }

    fun removeBook(book: BookDb, position: Int) {
        AppDatabase.getInstance(context).bookDao().deleteById(book.id!!)

        val event = RemoveBookEvent(book = book, position = position)
        EventBus.getDefault().post(event)
    }

    private fun mapShow(book: Book) = ModelMapper().map(book, BookDb::class.java)

    private fun getBookDetails(id: Int): Book {
        val tvShowsCall = libraryApi.getBookDetails(id)

        val response = tvShowsCall.execute()
        Log.d("Reponse", response.body().toString())
        if (response.code() != 200) {
            throw Exception("Result code is not 200")
        }
        return response.body()!!
    }

    private fun saveBook(book: Book): BookDb {
        val showDb = mapShow(book)
        AppDatabase.getInstance(context).bookDao().insert(showDb)
        return showDb
    }
}