package com.example.kotlin_library.ui.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_library.R
import com.example.kotlin_library.model.db.BookDb
import com.example.kotlin_library.ui.details.DetailsActivity
import com.example.kotlin_library.ui.main.MainActivity.Companion.BOOK_ID
import github.nisrulz.recyclerviewhelper.RVHAdapter
import github.nisrulz.recyclerviewhelper.RVHViewHolder
import kotlinx.android.synthetic.main.item_show.view.*
import java.util.*

class MainAdapter constructor(
    private val context: Context,
    private var books: MutableList<BookDb>,
    private val presenter: MainPresenter
) : RecyclerView.Adapter<MainAdapter.ViewHolder>(), RVHAdapter {

    private val IMGURL_BASE = "https://libraryapi20190416104651.azurewebsites.net"

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_show, viewGroup, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]

        holder.layout.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(BOOK_ID, book.id)
            context.startActivity(intent)
        }

        holder.title.text = book.title
        holder.author.text = book.author
        holder.publishYear.text = "Megjelenés éve: ${book.publishYear}"
        if (book.url != null && book.url!!.isNotEmpty())
            Glide.with(context).load(IMGURL_BASE + book.url).into(holder.poster)
        else
            Glide.with(context).load(R.drawable.noimage).into(holder.poster)
    }

    override fun getItemCount() = books.size

    override fun onItemDismiss(position: Int, direction: Int) {
        presenter.removeBook(books[position], position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        swap(fromPosition, toPosition)
        return false
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), RVHViewHolder {
        val layout = view.layout_item!!
        var title = view.tv_title!!
        var author = view.tv_author!!
        var publishYear = view.tv_publishYear!!
        var poster = view.iv_poster!!

        override fun onItemSelected(actionstate: Int) {
        }

        override fun onItemClear() {
        }
    }

    fun remove(position: Int) {
        books.remove(books[position])
        notifyItemRemoved(position)
    }

    private fun swap(firstPosition: Int, secondPosition: Int) {
        Collections.swap(books, firstPosition, secondPosition)
        notifyDataSetChanged()
    }

    fun update(books: MutableList<BookDb>) {
        this.books = books
        notifyDataSetChanged()
    }

    fun add(book: BookDb) {
        this.books.add(book)
        notifyItemInserted(books.size - 1)
    }
}