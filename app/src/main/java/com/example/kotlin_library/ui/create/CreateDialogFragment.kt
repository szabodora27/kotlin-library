package com.example.kotlin_library.ui.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kotlin_library.R
import com.example.kotlin_library.injector
import com.example.kotlin_library.model.db.BookDb
import kotlinx.android.synthetic.main.dialog_create.*
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class CreateDialogFragment : DialogFragment(), CreateScreen {
    @Inject
    lateinit var createPresenter: CreatePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save.setOnClickListener {
            val book = BookDb()
            book.id = Random.nextInt(5555, 555555555)
            book.title = et_title.text.toString()
            book.author = et_author.text.toString()
            book.shortDescription = et_description.text.toString()
            book.publishYear = if (et_publishYear.text.toString().isNotEmpty()) et_publishYear.text.toString().toInt() else 0
            book.numberOfPages = if (et_numberOfPages.text.toString().isNotEmpty()) et_numberOfPages.text.toString().toInt() else 0

            createPresenter.addBook(book)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector.inject(this)
        createPresenter.attachScreen(this)
    }

    override fun onDetach() {
        createPresenter.detachScreen()
        super.onDetach()
    }

    override fun dismissDialog() {
        dismiss()
    }
}