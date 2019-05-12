package com.example.kotlin_library

import com.example.kotlin_library.interactor.InteractorModule
import com.example.kotlin_library.mock.MockDatabaseModule
import com.example.kotlin_library.mock.MockNetworkModule
import com.example.kotlin_library.test.CreateBookTest
import com.example.kotlin_library.test.DetailsTest
import com.example.kotlin_library.test.MainTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule::class, TestModule::class, InteractorModule::class, MockDatabaseModule::class])
interface TestComponent : LibraryApplicationComponent {
    fun inject(mainTest: MainTest)
    fun inject(detailsTest: DetailsTest)
    fun inject(createShowTest: CreateBookTest)
}