package com.example.kotlin_library.test

import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.testInjector
import com.example.kotlin_library.ui.details.DetailsPresenter
import com.example.kotlin_library.ui.details.DetailsScreen
import com.example.kotlin_library.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import java.util.*
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class DetailsTest {
    @Inject
    lateinit var detailsPresenter: DetailsPresenter
    private lateinit var detailsScreen: DetailsScreen

    private val expectedBook = BookDb(
        555,
        "Test könyv",
        "Szabó Dóra",
        1995,
        "Saját kiadó",
        "Ez egy nagyon vicces könyv.",
        140,
        "Fejléc szöveg."
    )

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        detailsScreen = mock()
        detailsPresenter.attachScreen(detailsScreen)
    }

    @Test
    fun testGetBook() {
        detailsPresenter.getBook(555)
        Mockito.verify(detailsScreen).showBook(expectedBook)
    }

    @After
    fun tearDown() {
        detailsPresenter.detachScreen()
    }
}