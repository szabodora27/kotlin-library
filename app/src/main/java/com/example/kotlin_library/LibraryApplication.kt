package com.example.kotlin_library

import android.app.Application
import com.example.kotlin_library.db.DatabaseModule
import com.example.kotlin_library.ui.UIModule

class LibraryApplication : Application() {
    lateinit var injector: LibraryApplicationComponent

    override fun onCreate() {
        super.onCreate()

        injector = DaggerLibraryApplicationComponent.builder()
            .databaseModule(DatabaseModule(this))
            .uIModule(UIModule(this))
            .build()
    }
}