package com.kueski.tmdb.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kueski.tmdb.common.ApiError
import com.kueski.tmdb.domain.model.Movie
import com.kueski.tmdb.ui.viewmodel.MovieViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoviesByGenreScreen(
    navController: NavController,
    viewModel: MovieViewModel = koinViewModel<MovieViewModel>()
) {
    val moviesByGenreFlow = viewModel.getMoviesByGenreFlow()
    val moviesByGenre by moviesByGenreFlow.collectAsStateWithLifecycle(initialValue = emptyMap())
    val error by viewModel.error.collectAsStateWithLifecycle()

    if (error != null || moviesByGenre.isEmpty()) {
        val errorMessage = when (error) {
            ApiError.NetworkError -> "Network Error. Verify your connection."
            ApiError.UnknownError -> "Unknown Error."
            else -> "Unexpected Error."
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = errorMessage)
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = {
                    viewModel.clearError()
                    viewModel.fetchGenres()
                    viewModel.fetchMovies()
                }) {
                    Text(text = "Refresh")
                }
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            moviesByGenre.forEach { (genre, movies) ->
                item {
                    GenreSection(genre = genre, movies = movies, navController)
                }
            }
        }
    }
}

@Composable
fun GenreSection(
    genre: String,
    movies: List<Movie>,
    navController: NavController,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = genre,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie, onClick = {
                    navController.navigate("movieDetail/${movie.id}")
                })
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onClick: () -> Unit) {
    val baseImageUrl = "https://image.tmdb.org/t/p/w500"
    val backdropUrl = "$baseImageUrl${movie.backdropPath}"
    Card(
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = backdropUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startY = 300f
                        )
                    )
            )
            Text(
                text = movie.title,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomStart)
            )
        }
    }
}
