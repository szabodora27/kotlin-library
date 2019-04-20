package com.example.kotlin_library.model.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookDb(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: kotlin.Int? = null,
    @ColumnInfo(name = "title")
    var title: kotlin.String? = null,
    @ColumnInfo(name = "author")
    var author: kotlin.String? = null,
    @ColumnInfo(name = "publishYear")
    var publishYear: kotlin.Int? = null,
    @ColumnInfo(name = "publisher")
    var publisher: kotlin.String? = null,
    @ColumnInfo(name = "shortDescription")
    var shortDescription: kotlin.String? = null,
    @ColumnInfo(name = "numberOfPages")
    var numberOfPages: kotlin.Int? = null,
    @ColumnInfo(name = "headline")
    var headline: kotlin.String? = null
)