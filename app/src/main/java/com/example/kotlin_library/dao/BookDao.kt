package com.example.kotlin_library.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.model.network.Book

@Dao
public interface BookDao {
    @Query("SELECT * from book")
    fun getAllBook(): List<BookDb>

    @Query("SELECT * from book where id = :id")
    fun getBookById(id: Int): BookDb

    @Insert
    fun insert(showDb: BookDb)

    @Delete
    fun delete(showDb: BookDb)

    @Query("DELETE FROM book WHERE id = :id")
    fun deleteById(id: Int)

    @Query("DELETE FROM book")
    fun deleteAll()
}