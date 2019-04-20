package com.example.kotlin_library.interactor.books.events

import com.example.kotlin_library.model.db.BookDb

data class RemoveBookEvent (
    var code: Int = 0,
    var book: BookDb? = null,
    var position: Int? = null,
    var throwable: Throwable? = null
)