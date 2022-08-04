package com.irfanirawansukirman.movies.movies

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.irfanirawansukirman.cache.dao.MovieDao
import com.irfanirawansukirman.movies.data.cache.MoviesCacheRepository
import com.irfanirawansukirman.movies.data.cache.MoviesCacheRepositoryImpl
import com.irfanirawansukirman.movies.data.cache.mapper.MoviesCacheMapper
import com.irfanirawansukirman.movies.util.DataGenerator
import com.irfanirawansukirman.movies.util.MainCoroutinesRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
@SmallTest
class MoviesCacheRepositoryImplTest {

    @MockK
    private lateinit var movieDao: MovieDao

    private lateinit var repositoryImpl: MoviesCacheRepository

    private val moviesCacheMapper = MoviesCacheMapper()

    @Before
    fun `setup depends`() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

        repositoryImpl = MoviesCacheRepositoryImpl(movieDao, moviesCacheMapper)
    }

    @Test
    fun `get cache movies popular is success`() = runBlockingTest {
        val movies = DataGenerator.generateCacheMoviesPopulars()
        val expected = moviesCacheMapper.fromList(movies)

        coEvery { movieDao.getAllFavoriteMovies() } returns movies

        val response = repositoryImpl.getCacheMoviesPopular()

        coVerify { movieDao.getAllFavoriteMovies() }

        Truth.assertThat(response).containsAtLeastElementsIn(expected)
    }

    @Test(expected = Exception::class)
    fun `get cache movies popular is failed`() = runBlockingTest {
        coEvery { movieDao.getAllFavoriteMovies() } throws Exception()

        repositoryImpl.getCacheMoviesPopular()

        coVerify { movieDao.getAllFavoriteMovies() }
    }

    @After
    fun `clear all`() {
        clearAllMocks()
    }
}