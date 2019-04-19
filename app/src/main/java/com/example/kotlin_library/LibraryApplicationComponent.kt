package com.example.kotlin_library

import com.example.kotlin_library.interactor.InteractorModule
import com.example.kotlin_library.ui.UIModule
import com.example.kotlin_library.ui.details.DetailsActivity
import com.example.kotlin_library.ui.main.MainActivity
import com.example.kotlin_library.ui.create.CreateActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, InteractorModule::class])
interface LibraryApplicationComponent {
    fun inject(detailsActivity: DetailsActivity)
    fun inject(booksActivity: MainActivity)
    fun inject(createBookDialogFragment: CreateActivity)
}