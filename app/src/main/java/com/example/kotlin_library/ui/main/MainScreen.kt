package com.example.kotlin_library.ui.main

import com.example.kotlin_library.model.db.BookDb

interface MainScreen {
    fun showBooks(books: List<BookDb>?)
    fun addBook(book: BookDb)
    fun removeBook(book: BookDb, position: Int)
    fun showNetworkError(errorMsg: String)
}