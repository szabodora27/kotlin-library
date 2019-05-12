package com.example.kotlin_library.mock

import com.example.kotlin_library.model.network.Book
import com.example.kotlin_library.network.LibraryApi
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MockLibraryApi : LibraryApi {

    private val book = Book(
        555,
        "Test könyv",
        "Szabó Dóra",
        1995,
        "Saját kiadó",
        "Ez egy nagyon vicces könyv.",
        140,
        "Fejléc szöveg."
    )

    override fun getBooks(): Call<List<Book>> {
        val call = object : Call<List<Book>> {
            val books = listOf(book, book, book)

            @Throws(IOException::class)
            override fun execute(): Response<List<Book>> {
                return Response.success(books)
            }

            override fun enqueue(callback: Callback<List<Book>>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<List<Book>> {
                return this
            }

            override fun request(): Request? {
                return null
            }
        }

        return call
    }

    override fun getBookDetails(id: Int): Call<Book> {
        val call = object : Call<Book> {

            @Throws(IOException::class)
            override fun execute(): Response<Book> {
                return Response.success(book)
            }

            override fun enqueue(callback: Callback<Book>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<Book> {
                return this
            }

            override fun request(): Request? {
                return null
            }
        }

        return call
    }
}