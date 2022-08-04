package com.irfanirawansukirman.movies.movies

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.irfanirawansukirman.movies.domain.MoviesUseCaseImpl
import com.irfanirawansukirman.movies.presentation.movies.MoviesContract
import com.irfanirawansukirman.movies.presentation.movies.MoviesViewModel
import com.irfanirawansukirman.movies.presentation.movies.mapper.MoviesDomainUiMapper
import com.irfanirawansukirman.movies.util.BaseTest
import com.irfanirawansukirman.movies.util.DataGenerator
import com.irfanirawansukirman.remote.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MoviesViewModelTest : BaseTest() {

    @MockK
    private lateinit var useCase: MoviesUseCaseImpl

    private val mapper = MoviesDomainUiMapper()

    private lateinit var viewModel: MoviesViewModel

    override fun createDepends() {
        viewModel = MoviesViewModel(useCase, mapper)
    }

    @Test
    fun `get remote movies popular is success`() = runBlockingTest {
        val movies = DataGenerator.generateRemoteMoviesPopular()
        val successResponseFlow = flowOf(Resource.Success(movies))

        coEvery { useCase.getRemoteMoviesPopular() } returns successResponseFlow

        viewModel.uiState.test {
            viewModel.setEvent(MoviesContract.MoviesEvent.OnGetRemoteMoviesPopular)

            Truth.assertThat(expectItem()).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.Idle
                )
            )

            Truth.assertThat(expectItem()).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.Loading
                )
            )

            val expected = expectItem()
            val expectedMovies =
                (expected.moviesState as MoviesContract.MoviesState.SuccessRemoteGetMoviesPopular).movies
            Truth.assertThat(expected).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.SuccessRemoteGetMoviesPopular(
                        mapper.fromList(movies)
                    )
                )
            )
            Truth.assertThat(expectedMovies).containsExactlyElementsIn(mapper.fromList(movies))
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { useCase.getRemoteMoviesPopular() }
    }

    @Test
    fun `get remote movies popular is failed`() = runBlockingTest {
        val failedResponseFlow = flowOf(Resource.Error(Exception("Error")))

        coEvery { useCase.getRemoteMoviesPopular() } returns failedResponseFlow

        viewModel.uiState.test {
            viewModel.setEvent(MoviesContract.MoviesEvent.OnGetRemoteMoviesPopular)

            Truth.assertThat(expectItem()).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.Idle
                )
            )

            Truth.assertThat(expectItem()).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.Loading
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        viewModel.effect.test {
            val expected = expectItem()
            val expectedMessage = (expected as MoviesContract.MoviesEffect.ShowError).message
            Truth.assertThat(expected).isEqualTo(
                MoviesContract.MoviesEffect.ShowError("Error")
            )
            Truth.assertThat(expectedMessage).isEqualTo("Error")

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `insert movie popular is success`() = runBlockingTest {
        val entity = DataGenerator.generateMoviesData()
        val successResponseFlow = flowOf(Resource.Success("Success"))

        coEvery { useCase.insertMoviePopular(entity) } returns successResponseFlow

        viewModel.uiState.test {
            viewModel.setEvent(MoviesContract.MoviesEvent.OnInsertCacheMoviePopular(entity))

            Truth.assertThat(expectItem()).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.Idle
                )
            )

            Truth.assertThat(expectItem()).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.Loading
                )
            )

            val expected = expectItem()
            val expectedMessage =
                (expected.moviesState as MoviesContract.MoviesState.SuccessCacheInsertMoviePopular).message
            Truth.assertThat(expected).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.SuccessCacheInsertMoviePopular(
                        expectedMessage
                    )
                )
            )
            Truth.assertThat(expectedMessage).isEqualTo("Success")
            cancelAndIgnoreRemainingEvents()
        }

        coVerify { useCase.insertMoviePopular(entity) }
    }

    @Test
    fun `insert movie popular is failed`() = runBlockingTest {
        val entity = DataGenerator.generateMoviesData()
        val failedResponseFlow = flowOf(Resource.Error(Exception("Failed")))

        coEvery { useCase.insertMoviePopular(entity) } returns failedResponseFlow

        viewModel.uiState.test {
            viewModel.setEvent(MoviesContract.MoviesEvent.OnInsertCacheMoviePopular(entity))

            Truth.assertThat(expectItem()).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.Idle
                )
            )

            Truth.assertThat(expectItem()).isEqualTo(
                MoviesContract.State(
                    moviesState = MoviesContract.MoviesState.Loading
                )
            )

            cancelAndIgnoreRemainingEvents()
        }

        viewModel.effect.test {
            val expected = expectItem()
            val expectedMessage = (expected as MoviesContract.MoviesEffect.ShowError).message
            Truth.assertThat(expected).isEqualTo(
                MoviesContract.MoviesEffect.ShowError("Failed")
            )
            Truth.assertThat(expectedMessage).isEqualTo("Failed")

            cancelAndIgnoreRemainingEvents()
        }
    }
}