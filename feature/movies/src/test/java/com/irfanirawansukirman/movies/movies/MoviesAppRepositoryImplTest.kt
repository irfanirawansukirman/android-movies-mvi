package com.irfanirawansukirman.movies.movies

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.irfanirawansukirman.movies.data.MoviesAppRepositoryImpl
import com.irfanirawansukirman.movies.data.cache.MoviesCacheRepositoryImpl
import com.irfanirawansukirman.movies.data.mapper.MoviesDomainDataMapper
import com.irfanirawansukirman.movies.data.remote.repository.MoviesRemoteRepositoryImpl
import com.irfanirawansukirman.movies.util.DataGenerator
import com.irfanirawansukirman.remote.util.Resource
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
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MoviesAppRepositoryImplTest {

    @MockK
    private lateinit var moviesRemoteRepositoryImpl: MoviesRemoteRepositoryImpl

    @MockK
    private lateinit var moviesCacheRepositoryImpl: MoviesCacheRepositoryImpl

    private val moviesDomainDataMapper = MoviesDomainDataMapper()

    private lateinit var repository: MoviesAppRepositoryImpl

    @Before
    fun `setup depends`() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

        repository = MoviesAppRepositoryImpl(
            moviesRemoteRepositoryImpl,
            moviesCacheRepositoryImpl,
            moviesDomainDataMapper
        )
    }

    @Test
    fun `get remote movies popular is success`() = runBlockingTest {
        val movies = DataGenerator.generateMovisPopularData()

        coEvery { moviesRemoteRepositoryImpl.getMoviesPopular() } returns movies

        val response = repository.getRemoteMoviesPopular()
        response.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData)
                .containsAtLeastElementsIn(moviesDomainDataMapper.fromList(movies))
            expectComplete()
        }

        coVerify { moviesRemoteRepositoryImpl.getMoviesPopular() }
    }

    @Test
    fun `get remote movies popular is failed`() = runBlockingTest {
        coEvery { moviesRemoteRepositoryImpl.getMoviesPopular() } throws Exception()

        val response = repository.getRemoteMoviesPopular()
        response.test {
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        coVerify { moviesRemoteRepositoryImpl.getMoviesPopular() }
    }

    @Test
    fun `get cache movies popular is success`() = runBlockingTest {
        val movies = DataGenerator.generateMovisPopularData()

        coEvery { moviesCacheRepositoryImpl.getCacheMoviesPopular() } returns movies

        val response = repository.getCacheMoviesPopular()
        response.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData)
                .containsAtLeastElementsIn(moviesDomainDataMapper.fromList(movies))
            expectComplete()
        }

        coVerify { moviesCacheRepositoryImpl.getCacheMoviesPopular() }
    }

    @Test
    fun `get cache movies popular is failed`() = runBlockingTest {
        coEvery { moviesCacheRepositoryImpl.getCacheMoviesPopular() } throws Exception()

        val response = repository.getCacheMoviesPopular()
        response.test {
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        coVerify { moviesCacheRepositoryImpl.getCacheMoviesPopular() }
    }

    @Test
    fun `insert movie popular is failed`() = runBlockingTest {
        val entity = DataGenerator.generateMoviesData()

        coEvery { moviesCacheRepositoryImpl.insertMoviePopular(entity) } throws Exception()

        val response = repository.insertMoviePopular(entity)
        response.test {
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        coVerify { moviesCacheRepositoryImpl.insertMoviePopular(entity) }
    }

    @After
    fun `clear all`() {
        clearAllMocks()
    }
}