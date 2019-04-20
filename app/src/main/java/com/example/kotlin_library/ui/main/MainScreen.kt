package com.example.kotlin_library.ui.main

import com.example.kotlin_library.model.db.BookDb

interface MainScreen {
    fun showBooks(shows: List<BookDb>?)
    fun addBook(show: BookDb)
    fun removeBook(show: BookDb, position: Int)
    fun showNetworkError(errorMsg: String)
}