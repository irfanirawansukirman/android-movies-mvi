package com.irfanirawansukirman.movies.movies

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.irfanirawansukirman.movies.data.remote.mapper.MoviesNetworkMapper
import com.irfanirawansukirman.movies.data.remote.repository.MoviesRemoteRepository
import com.irfanirawansukirman.movies.data.remote.repository.MoviesRemoteRepositoryImpl
import com.irfanirawansukirman.movies.util.DataGenerator
import com.irfanirawansukirman.remote.BuildConfig
import com.irfanirawansukirman.remote.data.service.MovieService
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@SmallTest
class MoviesRemoteRepositoryImplTest {

    @MockK
    private lateinit var movieService: MovieService

    private lateinit var repositoryImpl: MoviesRemoteRepository

    private val moviesNetworkMapper = MoviesNetworkMapper()

    @Before
    fun `setup depends`() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

        repositoryImpl = MoviesRemoteRepositoryImpl(movieService, moviesNetworkMapper)
    }

    @Test
    fun `get remote movies popular is success`() = runBlockingTest {
        val movies = DataGenerator.generateResponseMoviesPopularData()

        coEvery { movieService.getMoviesPopular(BuildConfig.TMDB_API_KEY).results } returns movies

        val response = repositoryImpl.getMoviesPopular()

        coVerify { movieService.getMoviesPopular(BuildConfig.TMDB_API_KEY) }

        val expected = moviesNetworkMapper.fromList(movies)
        Truth.assertThat(response).containsExactlyElementsIn(expected)
    }

    @Test(expected = Exception::class)
    fun `get remote movies popular is failed`() = runBlockingTest {
        coEvery { movieService.getMoviesPopular(BuildConfig.TMDB_API_KEY).results } throws Exception()

        repositoryImpl.getMoviesPopular()

        coVerify { movieService.getMoviesPopular(BuildConfig.TMDB_API_KEY) }
    }

    @After
    fun `clear all`() {
        clearAllMocks()
    }
}