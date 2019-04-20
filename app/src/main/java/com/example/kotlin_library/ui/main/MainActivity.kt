package com.example.kotlin_library.ui.main

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_library.R
import com.example.kotlin_library.db.AppDatabase
import com.example.kotlin_library.injector
import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.ui.create.CreateDialogFragment
import com.google.android.material.snackbar.Snackbar
import github.nisrulz.recyclerviewhelper.RVHItemTouchHelperCallback
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), MainScreen {
    @Inject
    lateinit var mainPresenter: MainPresenter
    private var books: MutableList<BookDb> = mutableListOf()
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        injector.inject(this)

        rv_books.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(this, this.books, mainPresenter)
        rv_books.adapter = adapter
        val callback = RVHItemTouchHelperCallback(
            adapter, false, true,
            true
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(rv_books)

        mainPresenter.getBooks()
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                CreateDialogFragment().show(supportFragmentManager, "CREATE")
                true
            }
            R.id.refresh -> {
                val dialog = ProgressDialog.show(
                    this, "",
                    "Loading. Please wait...", true
                )
                thread {
                    AppDatabase.getInstance(this@MainActivity).bookDao().deleteAll()
                    mainPresenter.getBooks()
                }
                Handler().postDelayed({
                    dialog.dismiss()
                }, 5000)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun addBook(book: BookDb) {
        adapter.add(book)
        Snackbar.make(layout_main, getString(R.string.show_added), Snackbar.LENGTH_SHORT).show()
    }

    override fun removeBook(book: BookDb, position: Int) {
        adapter.remove(position)
    }

    override fun showBooks(books: List<BookDb>?) {
        adapter.update(books!!.toMutableList())
    }

    override fun showNetworkError(errorMsg: String) {
        Snackbar.make(layout_main, errorMsg, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        const val SHOW_ID = "SHOW_ID"
    }
}