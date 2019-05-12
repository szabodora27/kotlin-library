package com.example.kotlin_library.test

import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.testInjector
import com.example.kotlin_library.ui.create.CreatePresenter
import com.example.kotlin_library.ui.create.CreateScreen
import com.example.kotlin_library.ui.main.MainPresenter
import com.example.kotlin_library.ui.main.MainScreen
import com.example.kotlin_library.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class CreateBookTest {
    @Inject
    lateinit var createShowPresenter: CreatePresenter
    @Inject
    lateinit var mainPresenter: MainPresenter
    private lateinit var createShowScreen: CreateScreen
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
        createShowScreen = mock()
        mainScreen = mock()
        createShowPresenter.attachScreen(createShowScreen)
        mainPresenter.attachScreen(mainScreen)
    }

    @Test
    fun testAddBook() {
        createShowPresenter.addBook(expectedBook)
        Mockito.verify(createShowScreen).dismissDialog()
        Mockito.verify(mainScreen).addBook(expectedBook)
    }

    @After
    fun tearDown() {
        createShowPresenter.detachScreen()
        mainPresenter.detachScreen()
    }
}