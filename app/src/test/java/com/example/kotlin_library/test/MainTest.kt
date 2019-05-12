package com.example.kotlin_library.test

import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.testInjector
import com.example.kotlin_library.ui.main.MainPresenter
import com.example.kotlin_library.ui.main.MainScreen
import com.example.kotlin_library.utils.argumentCaptor
import com.example.kotlin_library.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class MainTest {
    @Inject
    lateinit var mainPresenter: MainPresenter
    private lateinit var mainScreen: MainScreen

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
        mainScreen = mock()
        mainPresenter.attachScreen(mainScreen)
    }

    @Test
    fun testGetBooks() {
        mainPresenter.getBooks()
        val list = argumentCaptor<List<BookDb>>()
        Mockito.verify(mainScreen).showBooks(list.capture())
        assert(list.value.isNotEmpty())
    }

    @Test
    fun testGetBooks2() {
        val expectedShows = listOf(expectedBook, expectedBook, expectedBook)
        mainPresenter.getBooks()
        Mockito.verify(mainScreen).showBooks(expectedShows)
    }

    @Test
    fun testRemoveBook() {
        mainPresenter.removeBook(expectedBook, 1)
        Mockito.verify(mainScreen).removeBook(expectedBook, 1)
    }

    @After
    fun tearDown() {
        mainPresenter.detachScreen()
    }
}