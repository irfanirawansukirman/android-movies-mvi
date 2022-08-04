package com.irfanirawansukirman.movies.util

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseTest {

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    abstract fun createDepends()

    @Before
    fun `setup depends`() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks
        createDepends()
    }

    @After
    fun `clear all`() {
        clearAllMocks()
    }
}