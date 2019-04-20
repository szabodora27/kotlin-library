package com.example.kotlin_library.network

import com.example.kotlin_library.model.network.Book
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LibraryApi {
    @GET("books")
    fun getBooks(): Call<List<Book>>

    @GET("books/{id}")
    fun getBookDetails(@Path("id") id: Int): Call<Book>
}