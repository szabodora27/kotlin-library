package com.example.kotlin_library.model.network

import com.google.gson.annotations.SerializedName

data class Book (
    @SerializedName("id")
    var id: kotlin.Int? = null,
    @SerializedName("title")
    var title: kotlin.String? = null,
    @SerializedName("author")
    var author: kotlin.String? = null,
    @SerializedName("publishYear")
    var publishYear: kotlin.Int? = null,
    @SerializedName("publisher")
    var publisher: kotlin.String? = null,
    @SerializedName("shortDescription")
    var shortDescription: kotlin.String? = null,
    @SerializedName("numberOfPages")
    var numberOfPages: kotlin.Int? = null,
    @SerializedName("headline")
    var headline: kotlin.String? = null
)