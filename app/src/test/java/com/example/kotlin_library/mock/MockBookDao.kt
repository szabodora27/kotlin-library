package com.example.kotlin_library.mock

import android.util.Log
import androidx.room.Dao
import com.example.kotlin_library.dao.BookDao
import com.example.kotlin_library.model.db.BookDb

@Dao
abstract class MockBookDao : BookDao {
    override fun getAllBook(): List<BookDb> {
        val book = BookDb(
            555,
            "Test könyv",
            "Szabó Dóra",
            1995,
            "Saját kiadó",
            "Ez egy nagyon vicces könyv.",
            140,
            "Fejléc szöveg."
        )
        return listOf(book, book, book)
    }

    override fun getBookById(id: Int): BookDb {
        return BookDb(
            555,
            "Test könyv",
            "Szabó Dóra",
            1995,
            "Saját kiadó",
            "Ez egy nagyon vicces könyv.",
            140,
            "Fejléc szöveg."
        )
    }

    override fun insert(bookDb: BookDb) {
        Log.d("Database", "Record inserted.")
    }

    override fun delete(bookDb: BookDb) {
        Log.d("Database", "Record deleted.")

    }

    override fun deleteById(id: Int) {
        Log.d("Database", "Record deleted by id.")
    }

    override fun deleteAll() {
        Log.d("Database", "Records deleted.")
    }
}