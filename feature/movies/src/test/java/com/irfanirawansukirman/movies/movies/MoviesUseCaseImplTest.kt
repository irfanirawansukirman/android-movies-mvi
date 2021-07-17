package com.irfanirawansukirman.movies.movies

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.irfanirawansukirman.movies.data.MoviesAppRepositoryImpl
import com.irfanirawansukirman.movies.domain.MoviesUseCaseImpl
import com.irfanirawansukirman.movies.util.DataGenerator
import com.irfanirawansukirman.movies.util.MainCoroutinesRule
import com.irfanirawansukirman.remote.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@SmallTest
class MoviesUseCaseImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutinesRule()

    @MockK
    private lateinit var appRepository: MoviesAppRepositoryImpl

    private lateinit var useCase: MoviesUseCaseImpl

    @Before
    fun `setup depends`() {
        MockKAnnotations.init(this, relaxUnitFun = true) // turn relaxUnitFun on for all mocks

        useCase = MoviesUseCaseImpl(appRepository)
    }

    @Test
    fun `get remote movies popular is success`() = runBlockingTest {
        val movies = DataGenerator.generateRemoteMoviesPopular()
        val successResponseFlow = flowOf(Resource.Success(movies))

        coEvery { appRepository.getRemoteMoviesPopular() } returns successResponseFlow

        val response = useCase.getRemoteMoviesPopular()
        response.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).containsAtLeastElementsIn(movies)
            expectComplete()
        }

        coVerify { appRepository.getRemoteMoviesPopular() }
    }

    @Test
    fun `get remote movies popular is failed`() = runBlockingTest {
        val failedResponseFlow = flowOf(Resource.Error(Exception()))

        coEvery { appRepository.getRemoteMoviesPopular() } returns failedResponseFlow

        val response = useCase.getRemoteMoviesPopular()
        response.test {
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        coEvery { appRepository.getRemoteMoviesPopular() }
    }

    @Test
    fun `get cache movies popular is success`() = runBlockingTest {
        val movies = DataGenerator.generateCacheMoviesPopular()
        val successResponseFlow = flowOf(Resource.Success(movies))

        coEvery { appRepository.getCacheMoviesPopular() } returns successResponseFlow

        val response = useCase.getCacheMoviesPopular()
        response.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).containsAtLeastElementsIn(movies)
            expectComplete()
        }

        coVerify { appRepository.getCacheMoviesPopular() }
    }

    @Test
    fun `get cache movies popular is failed`() = runBlockingTest {
        val failedResponseFlow = flowOf(Resource.Error(Exception()))

        coEvery { appRepository.getCacheMoviesPopular() } returns failedResponseFlow

        val response = useCase.getCacheMoviesPopular()
        response.test {
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }

        coEvery { appRepository.getCacheMoviesPopular() }
    }

    @Test
    fun `insert movie popular is success`() = runBlockingTest {
        val entity = DataGenerator.generateMoviesData()
        val successResponseFlow = flowOf(Resource.Success("Success"))

        coEvery { appRepository.insertMoviePopular(entity) } returns successResponseFlow

        val response = useCase.insertMoviePopular(entity)
        response.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).isEqualTo("Success")
            expectComplete()
        }
    }

    @Test
    fun `insert movie popular is failed`() = runBlockingTest {
        val entity = DataGenerator.generateMoviesData()
        val failedResponseFlow = flowOf(Resource.Error(Exception("Failed")))

        coEvery { appRepository.insertMoviePopular(entity) } returns failedResponseFlow

        val response = useCase.insertMoviePopular(entity)
        response.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Error).exception.message
            Truth.assertThat(expected).isInstanceOf(Resource.Error::class.java)
            Truth.assertThat(expectedData).isEqualTo("Failed")
            expectComplete()
        }

        coVerify { appRepository.insertMoviePopular(entity) }
    }

    @After
    fun `clear all`() {
        clearAllMocks()
    }
}