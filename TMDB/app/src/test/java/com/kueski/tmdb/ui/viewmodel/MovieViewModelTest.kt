package com.kueski.tmdb.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kueski.tmdb.common.ApiError
import com.kueski.tmdb.common.Result
import com.kueski.tmdb.domain.usecases.FetchGenresUseCase
import com.kueski.tmdb.domain.usecases.FetchMoviesUseCase
import com.kueski.tmdb.domain.usecases.RefreshGenresUseCase
import com.kueski.tmdb.domain.usecases.RefreshMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MovieViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fetchGenresUseCase: FetchGenresUseCase

    @Mock
    private lateinit var fetchMoviesUseCase: FetchMoviesUseCase

    @Mock
    private lateinit var refreshMoviesUseCase: RefreshMoviesUseCase

    @Mock
    private lateinit var refreshGenresUseCase: RefreshGenresUseCase

    private lateinit var movieViewModel: MovieViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        movieViewModel = MovieViewModel(
            fetchGenresUseCase,
            fetchMoviesUseCase,
            refreshMoviesUseCase,
            refreshGenresUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `fetchGenres should update error StateFlow to null on success`() = runBlockingTest {
        // Arrange
        `when`(refreshGenresUseCase()).thenReturn(Result.Success(Unit))

        // Act
        movieViewModel.fetchGenres()

        // Assert
        assert(movieViewModel.error.value == null)
    }

    @Test
    fun `fetchGenres should update error StateFlow on failure`() = runBlockingTest {
        // Arrange
        val mockError = ApiError.NetworkError
        `when`(refreshGenresUseCase()).thenReturn(Result.Error(mockError))

        // Act
        movieViewModel.fetchGenres()

        // Assert
        assert(movieViewModel.error.value == mockError)
    }

    @Test
    fun `fetchMovies should update error StateFlow to null on success`() = runBlockingTest {
        // Arrange
        `when`(refreshMoviesUseCase()).thenReturn(Result.Success(Unit))

        // Act
        movieViewModel.fetchMovies()

        // Assert
        assert(movieViewModel.error.value == null)
    }

    @Test
    fun `fetchMovies should update error StateFlow on failure`() = runBlockingTest {
        // Arrange
        val mockError = ApiError.NetworkError
        `when`(refreshMoviesUseCase()).thenReturn(Result.Error(mockError))

        // Act
        movieViewModel.fetchMovies()

        // Assert
        assert(movieViewModel.error.value == mockError)
    }
}
