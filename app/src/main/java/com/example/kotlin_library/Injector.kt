package com.example.kotlin_library

import android.app.Activity
import androidx.fragment.app.Fragment

val Activity.injector: LibraryApplicationComponent
    get() {
        return (this.applicationContext as LibraryApplication).injector
    }

val Fragment.injector: LibraryApplicationComponent
    get() {
        return (this.context!!.applicationContext as LibraryApplication).injector
    }