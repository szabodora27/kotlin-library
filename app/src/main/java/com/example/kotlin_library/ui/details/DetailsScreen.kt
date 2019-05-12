package com.example.kotlin_library.ui.details

import com.example.kotlin_library.model.db.BookDb

interface DetailsScreen {
    fun showBook(book: BookDb?)
    fun showNetworkError(errorMsg: String)
}