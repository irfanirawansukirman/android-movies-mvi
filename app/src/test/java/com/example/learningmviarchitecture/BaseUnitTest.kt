package com.example.learningmviarchitecture

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseUnitTest {

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var mockException: Exception

    @RelaxedMockK
    lateinit var context: Application

    @Before
    fun `setup depends`() {
        MockKAnnotations.init(this)
    }

    @After
    fun `clear all`() {
        clearAllMocks()
    }
}