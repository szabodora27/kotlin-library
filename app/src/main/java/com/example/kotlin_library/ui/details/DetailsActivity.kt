package com.example.kotlin_library.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kotlin_library.R
import com.example.kotlin_library.injector
import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.ui.main.MainActivity.Companion.BOOK_ID
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity(), DetailsScreen {
    @Inject
    lateinit var detailsPresenter: DetailsPresenter
    private val IMGURL_BASE = "https://libraryapi20190416104651.azurewebsites.net"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        detailsPresenter.attachScreen(this)
    }

    override fun onResume() {
        super.onResume()
        detailsPresenter.getBook(intent.getIntExtra(BOOK_ID, 100))
    }

    override fun onStop() {
        detailsPresenter.detachScreen()
        super.onStop()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showBook(book: BookDb?) {
        tv_title.text = book?.title
        tv_author.text = book?.author
        tv_description.text = book?.shortDescription
        tv_publishYear.text = "Megjelenés éve: ${book?.publishYear}"
        tv_pages.text = "Oldalak száma: ${book?.numberOfPages}"

        if (book?.url != null && book.url!!.isNotEmpty())
            Glide.with(this).load(IMGURL_BASE + book.url).into(iv_poster)
        else
            Glide.with(this).load(R.drawable.noimage).into(iv_poster)
    }

    override fun showNetworkError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }
}