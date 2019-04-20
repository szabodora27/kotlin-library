package com.example.kotlin_library.interactor.books.events

import com.example.kotlin_library.model.db.BookDb

data class GetBooksEvent (
    var code: Int = 0,
    var books: List<BookDb>? = null,
    var throwable: Throwable? = null
)