package com.example.learningmviarchitecture

import androidx.lifecycle.Observer
import com.example.learningmviarchitecture.data.model.Result
import com.example.learningmviarchitecture.data.repository.MoviesRepositoryImpl
import com.example.learningmviarchitecture.movies.MoviesContract
import com.example.learningmviarchitecture.movies.MoviesViewModel
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest : BaseUnitTest() {

    @RelaxedMockK
    private lateinit var moviesObserver: Observer<MoviesContract.MoviesState>

    @RelaxedMockK
    private lateinit var moviesRepositoryImpl: MoviesRepositoryImpl

    private lateinit var viewModel: MoviesViewModel

    private val movie = Result(
        false,
        "",
        listOf(1, 2, 3),
        0,
        "",
        "",
        "",
        0.0,
        "",
        "",
        "",
        false,
        0.0,
        0
    )

    @Before
    fun `init depends`() {
        viewModel = MoviesViewModel(moviesRepositoryImpl)
    }

    @Test
    fun `test dummy`() {
        assertEquals(1, 1)
    }
}
