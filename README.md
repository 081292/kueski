# Kueski
Challenge

Architecture (MVVM with Clean Architecture)

+---------------------------------------------------+
|                    UI Layer                       |
|   (Jetpack Compose)                               |
|---------------------------------------------------|
| - MoviesByGenreScreen                             |
|   - Display the list of Movies by Genre           |
| - MovieDetailsScreen                              |
|  - Display the details of a Movie                 |
+---------------------------------------------------+
            ↑
            | Call ViewModel
            ↓
+---------------------------------------------------+
|                Presentation Layer                 |
|   (ViewModel)                                     |
|---------------------------------------------------|
| - MoviesViewModel                                 |
|   - Controls the State of the UI (movies, error)  |
| - MovieDetailsViewModel                           |
|   - Controls the State of the UI (movie details)  |
+---------------------------------------------------+
            ↑
            | Call Use Case
            ↓
+---------------------------------------------------+
|                Domain Layer                       |
|   (Use Case)                                      |
|---------------------------------------------------|
| - FetchMoviesUseCase                              |
| - FetchMovieDetailsUseCase                        |
| - FetchGenresUseCase                              |
| - RefreshMoviesUseCase                            |
| - RefreshGenresUseCase                            |
+---------------------------------------------------+
            ↑
            | Call Repository
            ↓
+---------------------------------------------------+
|                Data Layer                         |
|   (Repository)                                    |
|---------------------------------------------------|
| - MovieRepository                                 |
| - GenreRepository                                 |
+---------------------------------------------------+
            ↑
            | Call API
            ↓
+---------------------------------------------------+
|                Framework Layer                    |
|   (API, Retrofit, Room, etc.)                     |
|---------------------------------------------------|
| - MovieApi (Retrofit, Room)                       |
+---------------------------------------------------+
