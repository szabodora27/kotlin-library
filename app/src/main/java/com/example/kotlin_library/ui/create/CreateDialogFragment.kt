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
            book.id = Random(565566).nextInt()
            book.title = "TESTING"
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