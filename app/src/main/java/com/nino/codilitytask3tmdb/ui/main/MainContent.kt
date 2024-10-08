package com.nino.codilitytask3tmdb.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.nino.codilitytask3tmdb.ui.moviedetail.MovieDetailScreen
import com.nino.codilitytask3tmdb.ui.moviedetail.MovieDetailViewModel
import com.nino.codilitytask3tmdb.ui.moviedetail.image.ImagesScreen
import com.nino.codilitytask3tmdb.ui.moviedetail.person.PeopleGridScreen
import com.nino.codilitytask3tmdb.ui.movies.MoviesScreen
import com.nino.codilitytask3tmdb.ui.navigation.ARG_INITIAL_PAGE
import com.nino.codilitytask3tmdb.ui.navigation.ARG_MOVIE_ID
import com.nino.codilitytask3tmdb.ui.navigation.ARG_PERSON_ID
import com.nino.codilitytask3tmdb.ui.navigation.Screen
import com.nino.codilitytask3tmdb.ui.profile.ProfileScreen

val LocalNavController = compositionLocalOf<NavHostController> { error("No nav controller") }
val LocalDarkTheme = compositionLocalOf { mutableStateOf(false) }

@Composable
fun MainContent() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = Screen.MOVIES.route) {
        composable(Screen.MOVIES.route) { MoviesScreen() }

        navigation(startDestination = Screen.DETAIL.route, route = "movie") {
            navArgument(ARG_MOVIE_ID) { type = NavType.StringType }

            fun NavBackStackEntry.movieId(): Int {
                return arguments?.getString(ARG_MOVIE_ID)!!.toInt()
            }

            val movieDetailViewModel: @Composable (movieId: Int) -> MovieDetailViewModel = { hiltViewModel() }

            composable(route = Screen.DETAIL.route) {
                MovieDetailScreen(movieDetailViewModel(it.movieId()))
            }

            composable(
                route = Screen.IMAGES.route,
                arguments = listOf(navArgument(ARG_INITIAL_PAGE) { defaultValue = "0" }),
            ) {
                val initialPage = it.arguments?.getString(ARG_INITIAL_PAGE)!!.toInt()
                val images = movieDetailViewModel(it.movieId()).uiState.collectAsState().value.images
                ImagesScreen(images, initialPage)
            }

            composable(route = Screen.CAST.route) {
                val cast = movieDetailViewModel(it.movieId()).uiState.collectAsState().value.credits.cast
                PeopleGridScreen(cast)
            }

            composable(route = Screen.CREW.route) {
                val crew = movieDetailViewModel(it.movieId()).uiState.collectAsState().value.credits.crew
                PeopleGridScreen(crew)
            }

            composable(route = Screen.PROFILE.route, arguments = listOf(navArgument(ARG_PERSON_ID) {})) {
                ProfileScreen(hiltViewModel())
            }
        }
    }
}
